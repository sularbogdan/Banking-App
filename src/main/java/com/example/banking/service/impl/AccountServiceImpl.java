package com.example.banking.service.impl;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountbyID(Long id) {
     Account account =   accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Contul nu exista!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
            Account account =   accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Contul nu exista!"));
           double total =  account.getBalance() + amount;
           account.setBalance(total);
           Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =   accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Contul nu exista!"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Sold insuficient!");
        }


        double total =  account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
       return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account) ).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {

        Account account =   accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Contul nu exista!"));
        accountRepository.deleteById(id);
    }
}
