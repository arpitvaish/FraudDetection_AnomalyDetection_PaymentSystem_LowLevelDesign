package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.exception.TransactionOutOfCountryException;
import com.paymentsystem.lld.model.Transaction;

public class TrasactionOutOfCountry implements IRule<Transaction, Transaction> {
    @Override
    public boolean isApplicable(Transaction input) {
        return input.getCountry() != input.getAccount().getCustomer().getCountry();
    }

    @Override
    public Transaction execute(Transaction input) {

        System.out.println("Transaction is not performed in same country as Customer!!!");
        throw new TransactionOutOfCountryException();

    }

}
