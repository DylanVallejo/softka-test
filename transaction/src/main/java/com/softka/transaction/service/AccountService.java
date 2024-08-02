package com.softka.transaction.service;


import com.softka.transaction.dto.AccountDto;
import com.softka.transaction.dto.AccountResponseDto;
import com.softka.transaction.exception.GeneralException;


public interface AccountService {

    AccountResponseDto createAccount(AccountDto accountDto) throws GeneralException;

}
