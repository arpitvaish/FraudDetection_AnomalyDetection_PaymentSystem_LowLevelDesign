package com.paymentsystem.lld.service;

import com.paymentsystem.lld.exception.CustomerNotFoundException;
import com.paymentsystem.lld.manager.AccountManager;
import com.paymentsystem.lld.manager.CustomerManager;
import com.paymentsystem.lld.model.Account;
import com.paymentsystem.lld.model.Customer;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AccountsService {

    private final AccountManager accountManager;
    private final CustomerManager customerManager;

    public AccountsService(AccountManager accountManager, CustomerManager customerManager) {
        this.accountManager = accountManager;
        this.customerManager = customerManager;
    }

    public Account createAccount(Customer customer, Account account) {
        String uuid = UUID.randomUUID().toString();
        String customerId = account.getCustomer().getId();
        try {
            customerManager.getCustomer(customer.getId());
        } catch (CustomerNotFoundException customerNotFoundException) {
            customerId = customerManager.createCustomer(customer);
        }
        account.getCustomer().setId(customerId);
        return accountManager.createAccount(account);


    }

    public Account getAccountDetails(String accountId) {
        return accountManager.getAccount(accountId);
    }
}
