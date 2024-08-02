package com.softka.transaction.service;


import com.softka.transaction.dto.AccountDto;
import com.softka.transaction.dto.AccountResponseDto;
import com.softka.transaction.entity.Account;
import com.softka.transaction.exception.GeneralException;

import java.util.List;


public interface AccountService {

    AccountResponseDto createAccount(AccountDto accountDto) throws GeneralException;


    List<Account> findAllByClientId(Long clientId);

    String deactivateAccount(AccountDto accountDto) throws  GeneralException;

}
