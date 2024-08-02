package com.softka.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
//@Builder
@AllArgsConstructor
public class ReportDto {

    private String client;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private List<ReportTransactionDto> transactionDtoList;


}
