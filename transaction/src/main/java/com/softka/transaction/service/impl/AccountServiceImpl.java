package com.softka.transaction.service.impl;

import com.softka.transaction.client.ClientFeignClient;
import com.softka.transaction.dto.AccountDto;
import com.softka.transaction.dto.AccountResponseDto;
import com.softka.transaction.dto.client.ClientResponseDto;
import com.softka.transaction.entity.Account;
import com.softka.transaction.exception.GeneralException;
import com.softka.transaction.mapper.Mapper;
import com.softka.transaction.repository.AccountRepository;
import com.softka.transaction.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ClientFeignClient clientFeignClient;
    private final Mapper mapper;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountResponseDto createAccount(AccountDto accountDto) throws GeneralException {
        try {
            ClientResponseDto callClientDB = clientFeignClient.findClientById(accountDto.getClientId());
            Account saveAccount = mapper.accountDtoToAccount(accountDto);
            saveAccount.setAccountNumber(UUID.randomUUID().toString().substring(0,8));
            saveAccount.setStatus(true);
            saveAccount.setClientId(callClientDB.getId());
            Account createdAccount = accountRepository.save(saveAccount);
            AccountResponseDto accountResponseDto =  mapper.accountToAccountResponseDto(createdAccount);
            accountResponseDto.setClientName(callClientDB.getName());
            return accountResponseDto;
        }catch (Exception exception){
            throw new GeneralException(exception.getMessage() );
        }
    }

    @Override
    public List<Account> findAllByClientId(Long clientId) {
        return accountRepository.findAllByClientId(clientId);
    }

    @Override
    public String deactivateAccount(AccountDto accountDto) throws GeneralException {
        Account accountDB =  accountRepository.findByAccountNumber(accountDto.getAccountNumber());
        accountDB.setStatus(accountDto.getStatus());
        accountRepository.save(accountDB);
        return "Account status whit number: " + accountDto.getAccountNumber() + " updated.";
    }

    @Override
    public AccountDto findAccountByAccountNumber(String accountNumber) throws GeneralException {
        try {
            Account account =  accountRepository.findByAccountNumber(accountNumber);
            return mapper.accountToAccountDto(account);
        }catch (Exception exception){
            throw new GeneralException(exception.getMessage());
        }
    }


}
