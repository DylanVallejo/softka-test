package com.softka.transaction.dto;


import com.softka.transaction.enums.AccountType;
import lombok.Data;

@Data
public class TransactionDto {

    private AccountType type;
    private Double amount;
    private String accountNumber;


}
