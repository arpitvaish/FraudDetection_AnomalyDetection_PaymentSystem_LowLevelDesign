package com.paymentsystem.lld.controller;

import com.paymentsystem.lld.model.Account;
import com.paymentsystem.lld.model.Customer;
import com.paymentsystem.lld.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountsService accountsService;

    @PostMapping(value = "/create/{customerId}/{withdrawlLimit}/{availableBalance}")
    public ResponseEntity<Account> createAccount(@PathVariable String customerId, @PathVariable double withdrawlLimit, @PathVariable double availableBalance) {

        Customer customer = Customer.builder()
                .id(customerId)
                .build();
        Account account = Account.builder()
                .customer(customer)
                .withDrawLimit(withdrawlLimit)
                .availableBalance(availableBalance)
                .build();

        Account accountId = accountsService.createAccount(customer, account);

        return ResponseEntity.status(201).body(accountId);
    }


    @GetMapping(value="/get/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId){
        return ResponseEntity.status(200).body(accountsService.getAccountDetails(accountId));
    }
}
