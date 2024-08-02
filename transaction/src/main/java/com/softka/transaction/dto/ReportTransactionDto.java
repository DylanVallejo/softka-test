package com.softka.transaction.dto;


import com.softka.transaction.enums.AccountType;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ReportTransactionDto {
    private LocalDate date;
    private AccountType type;
    private Double amount;
    private Double afterBalance;
}