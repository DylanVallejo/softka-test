package com.softka.transaction.entity;

import com.softka.transaction.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private LocalDate date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "before_balance")
    private Double beforeBalance;

    @Column(name = "after_balance") // BALANCE
    private Double afterBalance;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "state")
    private Boolean status;

}
