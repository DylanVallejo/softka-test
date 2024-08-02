package com.softka.transaction.service;


import com.softka.transaction.dto.TransactionDto;
import com.softka.transaction.dto.TransactionResponseDto;
import com.softka.transaction.entity.Transaction;
import com.softka.transaction.exception.BalanceInsuficientException;
import com.softka.transaction.exception.ClientNotFoundException;
import com.softka.transaction.exception.IncorrectValueException;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponseDto transaction(TransactionDto transactionDto) throws BalanceInsuficientException, ClientNotFoundException, IncorrectValueException;

    List<Transaction> findByAccountAndDate(Long accountId, LocalDate initialDate, LocalDate finalDate);


}
