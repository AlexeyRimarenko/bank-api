package com.example.banking.model;

//Transfer.java

public class Transfer {
 private long fromAccountId;
 private long toAccountId;
 private double amount;

 public Transfer() {
 }

 public Transfer(long fromAccountId, long toAccountId, double amount) {
     this.fromAccountId = fromAccountId;
     this.toAccountId = toAccountId;
     this.amount = amount;
 }

 public long getFromAccountId() {
     return fromAccountId;
 }

 public void setFromAccountId(long fromAccountId) {
     this.fromAccountId = fromAccountId;
 }

 public long getToAccountId() {
     return toAccountId;
 }

 public void setToAccountId(long toAccountId) {
     this.toAccountId = toAccountId;
 }

 public double getAmount() {
     return amount;
 }

 public void setAmount(double amount) {
     this.amount = amount;
 }
}
