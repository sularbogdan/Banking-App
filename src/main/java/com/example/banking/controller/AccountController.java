package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountId(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountbyID(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit REST API
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id , @RequestBody  Map<String, Double> request){

        AccountDto accountDto =  accountService.deposit(id, request.get("amount"));
      return ResponseEntity.ok(accountDto);


    }

    //withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id , @RequestBody  Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto  = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // get all accounts Rest api
    @GetMapping
    public ResponseEntity <List<AccountDto>> getAllAccounts(){
      List<AccountDto> accounts = accountService.getAllAccounts();
      return ResponseEntity.ok(accounts);
    }

    //delete account rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Cntul a fost sters!");
    }
}
