package com.paymentsystem.lld.service;

import com.paymentsystem.lld.manager.TransactionManager;
import com.paymentsystem.lld.model.Transaction;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionService {

    FraudDetectionService fraudDetectionService;
    AnomalyDetectionService anomalyDetectionService;
    TransactionManager transactionManager;

    public TransactionService(FraudDetectionService fraudDetectionService, AnomalyDetectionService anomalyDetectionService, TransactionManager transactionManager) {
        this.fraudDetectionService = fraudDetectionService;
        this.anomalyDetectionService = anomalyDetectionService;
        this.transactionManager = transactionManager;
    }

    public Transaction validateFraud(Transaction transaction) {
        transaction = fraudDetectionService.apply(transaction);
        return transaction;
    }

    public Transaction validateAnomaly(Transaction transaction) {
        transaction = anomalyDetectionService.apply(transaction);
        return transaction;
    }

    public Transaction save(Transaction transaction) {
        Transaction transaction1 = validateAnomaly(transaction);
        transaction1 = validateFraud(transaction1);
        transactionManager.createTransaction(transaction1);
        System.out.println("Saved Transaction");
        return transaction1;
    }
}
