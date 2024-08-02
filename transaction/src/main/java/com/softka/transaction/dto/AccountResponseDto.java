package com.softka.transaction.dto;

import com.softka.transaction.enums.AccountType;
import lombok.Data;

@Data
public class AccountResponseDto {

    private Long id;
    private String accountNumber;
    private AccountType type;
    private Double balance;
    private Boolean status;
    private String clientName;

}
