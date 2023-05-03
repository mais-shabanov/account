package com.example.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionType transactionType = TransactionType.INITIAL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(BigDecimal amount, LocalDateTime transactionDate, Account account) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }
}
