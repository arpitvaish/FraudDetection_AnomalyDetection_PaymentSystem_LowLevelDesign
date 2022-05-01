package com.paymentsystem.lld.manager;

import com.paymentsystem.lld.exception.AccountNotFoundException;
import com.paymentsystem.lld.exception.CustomerNotFoundException;
import com.paymentsystem.lld.model.Account;
import com.paymentsystem.lld.model.Customer;
import lombok.Synchronized;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AccountManager {

    private final ConcurrentHashMap<String, Account> accounts;

    public AccountManager() {
        this.accounts = new ConcurrentHashMap<>();

    }

    public Account getAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            return accounts.get(accountId);
        } else {
            throw new AccountNotFoundException();
        }
    }

    public synchronized String createAccount(Account account) {
        String uuid = UUID.randomUUID().toString();
        account.setId(uuid);
        accounts.put(account.getId(), account);
        return account.getId();
    }

    public List<Account> getCustomerAccount(Customer customer) {
        return accounts.values().stream().filter(a -> a.getCustomer().getId().equals(customer.getId())).collect(Collectors.toList());
    }
}
