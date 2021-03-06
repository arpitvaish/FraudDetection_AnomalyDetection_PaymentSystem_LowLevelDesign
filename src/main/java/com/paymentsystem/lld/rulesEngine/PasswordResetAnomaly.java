package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.exception.PasswordRestException;
import com.paymentsystem.lld.model.Transaction;
import com.paymentsystem.lld.model.TransactionType;

public class PasswordResetAnomaly implements IRule<Transaction,Transaction> {
    @Override
    public boolean isApplicable(Transaction input) {
        return input.getTransactionType().equals(TransactionType.PASSWORD_RESET) && !input.getCountry().equals(input.getAccount().getCustomer().getCountry());
    }

    @Override
    public Transaction execute(Transaction input) {
        input.setAnomaly(true);
        input.setAnomalyType("Password Anomaly");
        return input;
    }
}
