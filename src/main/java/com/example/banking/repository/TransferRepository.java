package com.example.banking.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import com.example.banking.model.Transfer;

@Repository
public class TransferRepository {
 private List<Transfer> transfers;

 public TransferRepository() {
     this.transfers = new ArrayList<>();
 }

 public void addTransfer(Transfer transfer) {
     transfers.add(transfer);
 }

 public List<Transfer> getTransfersByAccountId(long accountId) {
     List<Transfer> accountTransfers = new ArrayList<>();
     for (Transfer transfer : transfers) {
         if (transfer.getFromAccountId() == accountId || transfer.getToAccountId() == accountId) {
             accountTransfers.add(transfer);
         }
     }
     return accountTransfers;
 }
}
