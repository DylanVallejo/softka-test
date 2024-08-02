package com.softka.transaction.controller;

import com.softka.transaction.dto.TransactionDto;
import com.softka.transaction.dto.TransactionResponseDto;
import com.softka.transaction.exception.BalanceInsuficientException;
import com.softka.transaction.exception.ClientNotFoundException;
import com.softka.transaction.exception.IncorrectValueException;
import com.softka.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<TransactionResponseDto> generateTransactions(@RequestBody TransactionDto transactionDto) throws BalanceInsuficientException, ClientNotFoundException, IncorrectValueException {
        return new ResponseEntity<>(transactionService.transaction(transactionDto), HttpStatus.OK);
    }

}
