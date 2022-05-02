package com.paymentsystem.lld.manager;

import com.paymentsystem.lld.exception.LowFundsException;
import com.paymentsystem.lld.exception.TransactionNotFoundException;
import com.paymentsystem.lld.model.Account;
import com.paymentsystem.lld.model.Customer;
import com.paymentsystem.lld.model.Transaction;
import com.paymentsystem.lld.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Configuration
public class TransactionManager {

    private final ConcurrentHashMap<String, Transaction> transactions;
    @Autowired
    private AccountManager accountManager;

    public TransactionManager() {
        this.transactions = new ConcurrentHashMap<>();

    }

    public Transaction getTransaction(String transactionId) {
        if (transactions.containsKey(transactionId)) {
            return transactions.get(transactionId);
        } else {
            throw new TransactionNotFoundException();
        }
    }

    public synchronized Transaction createTransaction(Transaction transaction) {
        String uuid = UUID.randomUUID().toString();
        transaction.setId(uuid);
        Account account = accountManager.getAccount(transaction.getAccount().getId());
        Double balance = account.getAvailableBalance();
        Double transactionAmount = transaction.getAmount();
        TransactionType transactionType = transaction.getTransactionType();

        if (transactionType.equals(TransactionType.DEPOSIT)) {
            transaction.getAccount().setAvailableBalance(balance + transactionAmount);
            account.setAvailableBalance(balance + transactionAmount);
            accountManager.updateAccount(account);
        } else if (transactionType.equals(TransactionType.WITHDRAWL)) {
            if (balance < transaction.getAmount()) throw new LowFundsException();
            transaction.getAccount().setAvailableBalance(balance - transactionAmount);
            account.setAvailableBalance(balance - transactionAmount);
            accountManager.updateAccount(account);

        } else if (transactionType.equals(TransactionType.PASSWORD_RESET)) {
            //TODO : do something
        } else {
            throw new TransactionNotFoundException();
        }

        transactions.put(uuid, transaction);
        return transactions.get(uuid);
    }

    public List<Transaction> getCustomerTransactions(Customer customer) {
        return transactions.values().stream().filter(a -> a.getAccount().getCustomer().getId().equals(customer.getId())).collect(Collectors.toList());
    }

    public List<Transaction> getCustomerFraudDections(Customer customer) {
        return transactions.values().stream().filter(a -> a.getAccount().getCustomer().getId().equals(customer.getId())).filter(Transaction::isFraud).collect(Collectors.toList());
    }

    public List<Transaction> getCustomerAnomalyDetection(Customer customer) {
        return transactions.values().stream().filter(a -> a.getAccount().getCustomer().getId().equals(customer.getId())).filter(Transaction::isAnomaly).collect(Collectors.toList());
    }
}
