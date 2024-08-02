package com.softka.transaction.mapper;

import com.softka.transaction.dto.*;
import com.softka.transaction.entity.Account;
import com.softka.transaction.entity.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Mapper {

    public Account accountDtoToAccount(AccountDto accountDto){
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account, getNullPropertyNames(accountDto));
        return account;
    }

    public AccountDto accountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account, accountDto);
        return accountDto;
    }

    public AccountResponseDto accountToAccountResponseDto(Account account){
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        BeanUtils.copyProperties(account,accountResponseDto,getNullPropertyNames(account));
        return accountResponseDto;
    }

    public Transaction transactionDtoToTransaction(TransactionDto transactionDto, Double updatedBalance, Long accountId){
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction, getNullPropertyNames(transactionDto));
        transaction.setDate(LocalDate.now());
        transaction.setBeforeBalance(updatedBalance);
        transaction.setAfterBalance(updatedBalance);
        transaction.setAccountId(accountId);
        transaction.setStatus(true);
        return transaction;
    }


    public TransactionResponseDto  transactionToTransactionResponseDto(Transaction transaction){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        BeanUtils.copyProperties(transaction, transactionResponseDto);
        return transactionResponseDto;
    }

    public List<ReportTransactionDto> transactionsToTransactionsDtos(List<Transaction> transactions){
        return  transactions.stream()
                .map( transaction -> {
                    ReportTransactionDto transactionDto = new ReportTransactionDto();
                    BeanUtils.copyProperties(transaction, transactionDto,getNullPropertyNames(transaction));
                    return transactionDto;
                }).toList();

    }



    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

}
