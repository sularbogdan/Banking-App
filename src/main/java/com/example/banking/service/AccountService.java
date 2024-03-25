package com.example.banking.service;

import com.example.banking.dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);


    AccountDto getAccountbyID(Long id);

    AccountDto deposit(Long id , double amount);

    AccountDto withdraw(Long id , double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}