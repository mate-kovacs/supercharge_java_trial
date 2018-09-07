package com.supercharge.banking.Repository;

import com.supercharge.banking.model.Transaction;
import com.supercharge.banking.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserIdOrderByDateDesc(Long id);
    List<Transaction> findAllByUserIdAndTypeOrderByDateDesc(Long id, TransactionType type);
}
