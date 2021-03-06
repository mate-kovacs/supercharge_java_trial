package com.supercharge.banking.service;

import com.supercharge.banking.Repository.TransactionRepository;
import com.supercharge.banking.model.Transaction;
import com.supercharge.banking.model.TransactionType;
import com.supercharge.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

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
        source.isBalanceEnough(amount);
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

    public List<Transaction> getFullTransactionHistory(User user){
        return transactionRepository.findAllByUserIdOrderByDateDesc(user.getId());
    }

    public List<Transaction> getFilteredTransactionHistory(User user, TransactionType type){
        return transactionRepository.findAllByUserIdAndTypeOrderByDateDesc(user.getId(), type);
    }

    public List<Transaction> getFilteredTransactionHistory(User user, LocalDate start, LocalDate end){
        List<Transaction> history = transactionRepository.findAllByUserIdOrderByDateDesc(user.getId());
        Iterator<Transaction> iterator = history.listIterator();
        Transaction currentTransaction;
        while (iterator.hasNext()){
            currentTransaction = iterator.next();
            if (currentTransaction.getDate().toLocalDate().isBefore(start)) {
                iterator.remove();
            }
            if (currentTransaction.getDate().toLocalDate().isAfter(end)){
                iterator.remove();
            }
        }
        return history;
    }
}
