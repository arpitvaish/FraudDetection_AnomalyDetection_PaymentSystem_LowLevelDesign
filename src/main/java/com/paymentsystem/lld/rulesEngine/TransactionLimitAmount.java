package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.model.Transaction;

public class TransactionLimitAmount implements IRule<Transaction, Transaction> {
    @Override
    public boolean isApplicable(Transaction input) {
        double customerLimit = input.getAccount().getWithDrawLimit();
        return input.getAmount() > customerLimit;
    }

    @Override
    public Transaction execute(Transaction input) {
        input.setFraud(true);
        input.setFraudType("Transaction limit");
        return input;
    }
}
