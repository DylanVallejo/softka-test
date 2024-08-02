package com.softka.transaction.service;


import com.softka.transaction.client.ClientFeignClient;
import com.softka.transaction.dto.ReportDto;
import com.softka.transaction.dto.ReportRequestDto;
import com.softka.transaction.dto.ReportTransactionDto;
import com.softka.transaction.dto.client.ClientResponseDto;
import com.softka.transaction.entity.Account;
import com.softka.transaction.entity.Transaction;
import com.softka.transaction.exception.GeneralException;
import com.softka.transaction.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Reports {

    private final ClientFeignClient clientFeignClient;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final Mapper mapper;

    public List<ReportDto> createReport(Long clientId,LocalDate initialDate,LocalDate finalDate) throws GeneralException {
//        Long clientId = reportRequestDto.getClientId();
//        LocalDate initialDate = reportRequestDto.getInitialDate();
//        LocalDate finalDate = reportRequestDto.getFinalDate();

        ClientResponseDto clientData = clientFeignClient.findClientById(clientId);
        if (!clientData.getStatus()) throw new GeneralException("Client is not active.");

        List<Account> accounts = accountService.findAllByClientId(clientId);
        List<ReportDto> reportResponse = new ArrayList<>();

        for (Account account: accounts) {
            List<Transaction> transactions = transactionService.findByAccountAndDate(account.getId(), initialDate, finalDate);
            ReportDto  result = new ReportDto(
                    clientData.getName(),
                    account.getAccountNumber(),
                    account.getType().toString(),
                    account.getBalance(),
                    mapper.transactionsToTransactionsDtos(transactions)
                    );
            reportResponse.add(result);
        }
        return  reportResponse;
    }



}
