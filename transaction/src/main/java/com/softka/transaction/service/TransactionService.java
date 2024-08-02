package com.softka.transaction.service;


import com.softka.transaction.dto.TransactionDto;
import com.softka.transaction.dto.TransactionResponseDto;
import com.softka.transaction.exception.BalanceInsuficientException;
import com.softka.transaction.exception.ClientNotFoundException;
import com.softka.transaction.exception.IncorrectValueException;

public interface TransactionService {

    TransactionResponseDto transaction(TransactionDto transactionDto) throws BalanceInsuficientException, ClientNotFoundException, IncorrectValueException;

}
