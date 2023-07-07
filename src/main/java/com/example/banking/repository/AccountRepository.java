package com.example.banking.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.banking.model.Account;

@Repository
public class AccountRepository {
 private Map<Long, Account> accounts;

 public AccountRepository() {
     this.accounts = new HashMap<>();
 }

 public void addAccount(Account account) {
     accounts.put(account.getId(), account);
 }

 public Account getAccountById(long accountId) {
     return accounts.get(accountId);
 }

 public List<Account> getAllAccounts() {
     return new ArrayList<>(accounts.values());
 }

 public List<Account> getAccountsByCustomerId(long customerId) {
     List<Account> customerAccounts = new ArrayList<>();
     for (Account account : accounts.values()) {
         if (account.getCustomerId() == customerId) {
             customerAccounts.add(account);
         }
     }
     return customerAccounts;
 }
}
