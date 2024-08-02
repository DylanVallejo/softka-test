package com.softka.transaction.controller;

import com.softka.transaction.dto.ReportDto;
import com.softka.transaction.dto.ReportRequestDto;
import com.softka.transaction.dto.TransactionDto;
import com.softka.transaction.dto.TransactionResponseDto;
import com.softka.transaction.exception.BalanceInsuficientException;
import com.softka.transaction.exception.ClientNotFoundException;
import com.softka.transaction.exception.GeneralException;
import com.softka.transaction.exception.IncorrectValueException;
import com.softka.transaction.service.Reports;
import com.softka.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final Reports reports;

    @PostMapping()
    public ResponseEntity<TransactionResponseDto> generateTransactions(@RequestBody TransactionDto transactionDto) throws BalanceInsuficientException, ClientNotFoundException, IncorrectValueException {
        return new ResponseEntity<>(transactionService.transaction(transactionDto), HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<List<ReportDto>> createReportByDates(
            @RequestParam("clientId") Long clientId,
            @RequestParam("initialDate") LocalDate initialDate,
            @RequestParam("initialDate") LocalDate finalDate
            ) throws GeneralException {
        return  new ResponseEntity<>(reports.createReport(clientId, initialDate, finalDate), HttpStatus.OK);

    }

}
