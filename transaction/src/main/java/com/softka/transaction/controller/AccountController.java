package com.softka.transaction.controller;


import com.softka.transaction.dto.AccountDto;
import com.softka.transaction.dto.AccountResponseDto;
import com.softka.transaction.exception.GeneralException;
import com.softka.transaction.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
