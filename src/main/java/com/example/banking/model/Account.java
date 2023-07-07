package com.example.banking.model;

//Account.java

public class Account {
 private long id;
 private long customerId;
 private double balance;

 public Account() {
 }

 public Account(long id, long customerId, double balance) {
     this.id = id;
     this.customerId = customerId;
     this.balance = balance;
 }

 public long getId() {
     return id;
 }

 public void setId(long id) {
     this.id = id;
 }

 public long getCustomerId() {
     return customerId;
 }

 public void setCustomerId(long customerId) {
     this.customerId = customerId;
 }

 public double getBalance() {
     return balance;
 }

 public void setBalance(double balance) {
     this.balance = balance;
 }
}
