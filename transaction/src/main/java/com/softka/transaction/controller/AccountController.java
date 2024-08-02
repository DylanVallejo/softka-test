package com.softka.transaction.controller;


import com.softka.transaction.dto.AccountDto;
import com.softka.transaction.dto.AccountResponseDto;
import com.softka.transaction.entity.Account;
import com.softka.transaction.exception.GeneralException;
import com.softka.transaction.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDto> createAccountForClient(@RequestBody AccountDto accountDto ) throws GeneralException {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<String> deactivateAccount(@RequestBody AccountDto accountDto) throws GeneralException{
        return   new ResponseEntity<>(accountService.deactivateAccount(accountDto), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> findAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) throws GeneralException {
        return new ResponseEntity<>(accountService.findAccountByAccountNumber(accountNumber), HttpStatus.OK);
    }

    @GetMapping("/list/{clientId}")
    public ResponseEntity<List<Account>> findClientAccountByClientId(@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(accountService.findAllByClientId(clientId), HttpStatus.OK);
    }


}
