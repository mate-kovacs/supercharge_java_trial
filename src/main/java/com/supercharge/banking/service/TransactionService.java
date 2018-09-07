package com.supercharge.banking.service;

import com.supercharge.banking.Repository.TransactionRepository;
import com.supercharge.banking.model.Transaction;
import com.supercharge.banking.model.TransactionType;
import com.supercharge.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    final
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public boolean Deposit(User user, Long amount) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setTarget(null);
        transaction.setType(TransactionType.Deposit);
        user.setBalance(user.getBalance() + amount);
        try {
            transactionRepository.save(transaction);
        } catch (DataAccessException exception){
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean Withdraw(User user, Long amount) {
        user.isBalanceEnough(amount);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setTarget(null);
        transaction.setType(TransactionType.Withdraw);
        user.setBalance(user.getBalance() - amount);
        try {
            transactionRepository.save(transaction);
        } catch (DataAccessException exception){
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean Transfer(User source, User target, Long amount) {
        // todo check if source user's balance can support the withdrawal
        Transaction transaction = new Transaction();
        transaction.setUser(source);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setTarget(target);
        transaction.setType(TransactionType.Transfer);
        source.setBalance(source.getBalance() - amount);
        target.setBalance(target.getBalance() + amount);
        try {
            transactionRepository.save(transaction);
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }
}
