package com.example.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.banking.controller.BankController;
import com.example.banking.model.Account;
import com.example.banking.model.Customer;
import com.example.banking.model.Transfer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.TransferRepository;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DemoApplicationTests {
    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private BankController bankController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();
    }

    @Test
    public void createCustomerTest() throws Exception {
        Customer customer = new Customer(1, "John Doe");

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"John Doe\"}"))
                .andExpect(status().isCreated());

        verify(customerRepository, times(1)).addCustomer(customer);
    }

    @Test
    public void createAccountTest() throws Exception {
        Account account = new Account(1, 1, 100.0);

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"customerId\": 1, \"balance\": 100.0}"))
                .andExpect(status().isCreated());

        verify(accountRepository, times(1)).addAccount(account);
    }

    @Test
    public void makeTransferTest() throws Exception {
        Account fromAccount = new Account(1, 1, 200.0);
        Account toAccount = new Account(2, 2, 100.0);
        Transfer transfer = new Transfer(1, 2, 50.0);

        when(accountRepository.getAccountById(1)).thenReturn(fromAccount);
        when(accountRepository.getAccountById(2)).thenReturn(toAccount);

        mockMvc.perform(post("/api/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fromAccountId\": 1, \"toAccountId\": 2, \"amount\": 50.0}"))
                .andExpect(status().isCreated());

        verify(accountRepository, times(1)).getAccountById(1);
        verify(accountRepository, times(1)).getAccountById(2);
        verify(transferRepository, times(1)).addTransfer(transfer);
        verify(accountRepository, times(1)).addAccount(fromAccount);
        verify(accountRepository, times(1)).addAccount(toAccount);
    }

    @Test
    public void getAccountBalanceTest() throws Exception {
        Account account = new Account(1, 1, 150.0);

        when(accountRepository.getAccountById(1)).thenReturn(account);

        mockMvc.perform(get("/api/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("150.0"));

        verify(accountRepository, times(1)).getAccountById(1);
    }

    @Test
    public void getAccountTransfersTest() throws Exception {
        Account account = new Account(1, 1, 200.0);
        Transfer transfer1 = new Transfer(1, 2, 50.0);
        Transfer transfer2 = new Transfer(1, 3, 75.0);
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(transfer1);
        transfers.add(transfer2);

        when(accountRepository.getAccountById(1)).thenReturn(account);
        when(transferRepository.getTransfersByAccountId(1)).thenReturn(transfers);

        MvcResult result = mockMvc.perform(get("/api/accounts/1/transfers"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        // Assert on the content as per your requirement

        verify(accountRepository, times(1)).getAccountById(1);
        verify(transferRepository, times(1)).getTransfersByAccountId(1);
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "John Doe"));
        customers.add(new Customer(2, "Jane Smith"));

        when(customerRepository.getAllCustomers()).thenReturn(customers);

        MvcResult result = mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        // Assert on the content as per your requirement

        verify(customerRepository, times(1)).getAllCustomers();
    }

    @Test
    public void getAllAccountsTest() throws Exception {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1, 100.0));
        accounts.add(new Account(2, 1, 200.0));

        when(accountRepository.getAllAccounts()).thenReturn(accounts);

        MvcResult result = mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        // Assert on the content as per your requirement

        verify(accountRepository, times(1)).getAllAccounts();
    }
}
