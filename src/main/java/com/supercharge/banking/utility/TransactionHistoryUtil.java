package com.supercharge.banking.utility;

import com.supercharge.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryUtil {

    final
    private TransactionService transactionService;

    @Autowired
    public TransactionHistoryUtil(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
