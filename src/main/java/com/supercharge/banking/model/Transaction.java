package com.supercharge.banking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private User user;

    private LocalDateTime date;

    private long amount;

    private User target;

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    @Override
    public String toString() {
        StringBuilder transactionStringBuilder = new StringBuilder();
        transactionStringBuilder.append(type);
        transactionStringBuilder.append(", ");
        transactionStringBuilder.append(amount);
        transactionStringBuilder.append(", ");
        if (target != null) {
            transactionStringBuilder.append("To: ");
            transactionStringBuilder.append(target.getName());
            transactionStringBuilder.append(", ");
        }
        transactionStringBuilder.append("Date: ");
        transactionStringBuilder.append(date.toLocalDate());
        transactionStringBuilder.append(" ");
        transactionStringBuilder.append(date.toLocalTime());
        return transactionStringBuilder.toString();
    }
}
