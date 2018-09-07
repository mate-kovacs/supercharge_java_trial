package com.supercharge.banking.utility;

import com.supercharge.banking.model.Transaction;
import com.supercharge.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryUtil {

    final
    private TransactionService transactionService;

    @Autowired
    public TransactionHistoryUtil(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void printTransactionHistory(List<Transaction> history) {
        System.out.println("Transaction history:");
        for (Transaction transaction: history) {
            System.out.println(history.toString());
        }
    }
}
