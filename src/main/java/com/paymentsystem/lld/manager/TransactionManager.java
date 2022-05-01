package com.paymentsystem.lld.manager;

import com.paymentsystem.lld.exception.TransactionNotFoundException;
import com.paymentsystem.lld.model.Customer;
import com.paymentsystem.lld.model.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TransactionManager {

    private final ConcurrentHashMap<String, Transaction> transactions;

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

    public synchronized String createTransaction(Transaction transaction) {
        String uuid = UUID.randomUUID().toString();
        transaction.setId(uuid);
        transactions.put(transaction.getId(), transaction);
        return transaction.getId();
    }

    public List<Transaction> getCustomerTransactions(Customer customer) {
        return transactions.values().stream().filter(a -> a.getAccount().getCustomer().getId().equals(customer.getId())).collect(Collectors.toList());
    }

    public List<Transaction> getCustomerFraudDections(Customer customer) {
        return transactions.values().stream().filter(a -> a.getAccount().getCustomer().getId().equals(customer.getId())).filter(a -> a.isFraud()).collect(Collectors.toList());
    }

    public List<Transaction> getCustomerAnomalyDetection(Customer customer) {
        return transactions.values().stream().filter(a -> a.getAccount().getCustomer().getId().equals(customer.getId())).filter(a -> a.isAnomaly()).collect(Collectors.toList());
    }
}
