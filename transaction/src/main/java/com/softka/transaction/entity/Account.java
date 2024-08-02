package com.softka.transaction.entity;


import com.softka.transaction.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "state")
    private Boolean status;

    @Column(name = "client_id")
    private Long clientId;


}
