package com.example.banking.controller;

import java.util.List;

//BankController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransferRepository;
import com.example.banking.model.Account;
import com.example.banking.model.Customer;
import com.example.banking.model.Transfer;

@RestController
@RequestMapping("/api")
public class BankController {
 private final CustomerRepository customerRepository;
 private final AccountRepository accountRepository;
 private final TransferRepository transferRepository;

 @Autowired
 public BankController(
         CustomerRepository customerRepository,
         AccountRepository accountRepository,
         TransferRepository transferRepository
 ) {
     this.customerRepository = customerRepository;
     this.accountRepository = accountRepository;
     this.transferRepository = transferRepository;
 }

 @PostMapping("/customers")
 public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
     customerRepository.addCustomer(customer);
     return new ResponseEntity<>(customer, HttpStatus.CREATED);
 }

 @PostMapping("/accounts")
 public ResponseEntity<Account> createAccount(@RequestBody Account account) {
     accountRepository.addAccount(account);
     return new ResponseEntity<>(account, HttpStatus.CREATED);
 }

 @PostMapping("/transfers")
 public ResponseEntity<Transfer> makeTransfer(@RequestBody Transfer transfer) {
     Account fromAccount = accountRepository.getAccountById(transfer.getFromAccountId());
     Account toAccount = accountRepository.getAccountById(transfer.getToAccountId());

     if (fromAccount == null || toAccount == null) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

     double amount = transfer.getAmount();
     if (fromAccount.getBalance() < amount) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

     fromAccount.setBalance(fromAccount.getBalance() - amount);
     toAccount.setBalance(toAccount.getBalance() + amount);

     transferRepository.addTransfer(transfer);

     return new ResponseEntity<>(transfer, HttpStatus.CREATED);
 }

 @GetMapping("/accounts/{accountId}/balance")
 public ResponseEntity<Double> getAccountBalance(@PathVariable long accountId) {
     Account account = accountRepository.getAccountById(accountId);

     if (account == null) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

     return new ResponseEntity<>(account.getBalance(), HttpStatus.OK);
 }

 @GetMapping("/accounts/{accountId}/transfers")
 public ResponseEntity<List<Transfer>> getAccountTransfers(@PathVariable long accountId) {
     Account account = accountRepository.getAccountById(accountId);

     if (account == null) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

     List<Transfer> transfers = transferRepository.getTransfersByAccountId(accountId);

     return new ResponseEntity<>(transfers, HttpStatus.OK);
 }

 @GetMapping("/customers")
 public ResponseEntity<List<Customer>> getAllCustomers() {
     List<Customer> customers = customerRepository.getAllCustomers();
     return new ResponseEntity<>(customers, HttpStatus.OK);
 }

 @GetMapping("/accounts")
 public ResponseEntity<List<Account>> getAllAccounts() {
     List<Account> accounts = accountRepository.getAllAccounts();
     return new ResponseEntity<>(accounts, HttpStatus.OK);
 }
}
