package net.javaguides.banking.controller;


import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.RequestDTO;
import net.javaguides.banking.service.AccountService;
import org.hibernate.engine.spi.Resolution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController (AccountService accountService) {
        this.accountService = accountService;
    }

    //Add account using REST  API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount( @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposit rest api
    @PutMapping("/deposit")
    public ResponseEntity<AccountDto> deposit(
            @RequestBody RequestDTO request) {
        AccountDto accountDto = accountService.deposit(request.getId(),request.getAmount());
        return ResponseEntity.ok(accountDto);
    }


    //withdraw rest api
    @PutMapping("/withdraw")
    public ResponseEntity <AccountDto> withdraw (
            @RequestBody RequestDTO request) {
        AccountDto accountDto = accountService.withdraw(request.getId(),request.getAmount());
        return ResponseEntity.ok(accountDto);
    }

    //WITHDRAW RESTAPI ALTERNATIVE

//    @PutMapping
//    public ResponseEntity<AccountDto> withdraw ( @PathVariable Long id,
//                                                 @RequestBody Map<String,Double> request) {
//        double amount = request.get("amount");
//        AccountDto accountDto = accountService.withdraw(id,amount);
//        return ResponseEntity.ok(accountDto);
//    }


    //GET all accounts with rest api
    @GetMapping("/getall")
    public ResponseEntity<List<AccountDto>> getall () {
        List<AccountDto> accountDto = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDto);
    }
//    ALTERNATIVE
//    @GetMapping
//    public ResponseEntity <List<AccountDto>> getAllAccounts() {
//        List <AccountDto> accounts  = accountService.getAllAccounts();
//        return ResponseEntity.ok(accounts);
//    }


    //DELETE ACCOUNT RESTAPI
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted succesfully!");
    }

}

