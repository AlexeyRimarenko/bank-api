package com.example.banking.repository;

//CustomerRepository.java

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.banking.model.Customer;

@Repository
public class CustomerRepository {
 private Map<Long, Customer> customers;

 public CustomerRepository() {
     this.customers = new HashMap<>();
 }

 public void addCustomer(Customer customer) {
     customers.put(customer.getId(), customer);
 }

 public Customer getCustomerById(long customerId) {
     return customers.get(customerId);
 }

 public List<Customer> getAllCustomers() {
     return new ArrayList<>(customers.values());
 }
}
