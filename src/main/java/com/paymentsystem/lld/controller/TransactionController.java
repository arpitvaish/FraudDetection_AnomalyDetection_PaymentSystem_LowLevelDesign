package com.paymentsystem.lld.controller;

import com.paymentsystem.lld.manager.AccountManager;
import com.paymentsystem.lld.manager.TransactionManager;
import com.paymentsystem.lld.model.Account;
import com.paymentsystem.lld.model.Transaction;
import com.paymentsystem.lld.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionManager transactionManager;
    @Autowired
    private AccountManager accountManager;

    @PostMapping(value="/create/{transactionType}/{amount}/{accountId}")
    public ResponseEntity<Transaction> createTransaction(@PathVariable TransactionType transactionType, @PathVariable Double amount, @PathVariable String accountId){
        Account account = Account.builder()
                .id(accountId)
                .build();
        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(transactionType)
                .amount(amount)
                .build();
        Transaction transaction1 = transactionManager.createTransaction(transaction);
        return ResponseEntity.status(200).body(transaction1);

    }

}
