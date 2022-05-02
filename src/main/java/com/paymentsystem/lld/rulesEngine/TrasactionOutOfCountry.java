package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.exception.TransactionOutOfCountryException;
import com.paymentsystem.lld.model.Transaction;

public class TrasactionOutOfCountry implements IRule<Transaction, Transaction> {
    @Override
    public boolean isApplicable(Transaction input) {
        return !input.getCountry().equals(input.getAccount().getCustomer().getCountry());
    }

    @Override
    public Transaction execute(Transaction input) {

        input.setFraud(true);
        input.setFraudType("Out of country transaction");
        return input;

    }

}
