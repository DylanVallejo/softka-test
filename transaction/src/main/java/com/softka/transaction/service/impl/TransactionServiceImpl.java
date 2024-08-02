package com.softka.transaction.service.impl;

import com.softka.transaction.dto.TransactionDto;
import com.softka.transaction.dto.TransactionResponseDto;
import com.softka.transaction.entity.Account;
import com.softka.transaction.entity.Transaction;
import com.softka.transaction.enums.AccountType;
import com.softka.transaction.exception.BalanceInsuficientException;
import com.softka.transaction.exception.ClientNotFoundException;
import com.softka.transaction.exception.IncorrectValueException;
import com.softka.transaction.mapper.Mapper;
import com.softka.transaction.repository.AccountRepository;
import com.softka.transaction.repository.TransactionRepository;
import com.softka.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final Mapper mapper;


    @Override
    public TransactionResponseDto transaction(TransactionDto transactionDto) throws BalanceInsuficientException, ClientNotFoundException, IncorrectValueException {

        TransactionResponseDto response = null;
        if (transactionDto.getType().equals(AccountType.DEBIT)){
            response = debit(transactionDto);
        } else if (transactionDto.getType().equals(AccountType.CREDIT)) {
            response = credit(transactionDto);
        }
        return response;

    }

    public Account findAccount(TransactionDto transactionDto){
        return accountRepository.findByAccountNumber(transactionDto.getAccountNumber());
    }

    public TransactionResponseDto debit(TransactionDto debitTransaction) throws BalanceInsuficientException, IncorrectValueException {
        Account accountDB = findAccount(debitTransaction);
        if(accountDB.getBalance() < Math.abs(debitTransaction.getAmount())){
            throw new BalanceInsuficientException("Current balance is insufficient for the transaction, actual balance" + accountDB.getBalance());
        } else if ( debitTransaction.getAmount() >= 0  ) {
            throw new IncorrectValueException("Debits only allows negative numbers.");
        }
        accountDB.setBalance(accountDB.getBalance() - Math.abs(debitTransaction.getAmount()));
        accountRepository.save(accountDB);
        Transaction generateTransaction =  mapper.transactionDtoToTransaction(debitTransaction, accountDB.getBalance(), accountDB.getId() );
        Transaction transactionDB = transactionRepository.save(generateTransaction);
        return mapper.transactionToTransactionResponseDto(transactionDB);
    }

    public TransactionResponseDto credit(TransactionDto creditTransaction) throws IncorrectValueException {
        Account accountDB = findAccount(creditTransaction);
        if (creditTransaction.getAmount() <= 0) {
            throw new IncorrectValueException("Credits only allows positive numbers.");
        }
        accountDB.setBalance(accountDB.getBalance() + creditTransaction.getAmount());
        accountRepository.save(accountDB);
        Transaction transaction = mapper.transactionDtoToTransaction(creditTransaction,accountDB.getBalance(),accountDB.getId());
        Transaction transactionDB = transactionRepository.save(transaction);
        return mapper.transactionToTransactionResponseDto(transactionDB);
    }


}
