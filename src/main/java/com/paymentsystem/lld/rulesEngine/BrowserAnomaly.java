package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.exception.BrowserNotSupportedException;
import com.paymentsystem.lld.model.Transaction;

public class BrowserAnomaly implements IRule<Transaction, Transaction> {
    @Override
    public boolean isApplicable(Transaction input) {
        return input.getBrowser().getBrowserType() == null;
    }

    @Override
    public Transaction execute(Transaction input) {
        input.setAnomaly(true);
        input.setAnomalyType("Browser Anomaly");
        return input;
    }
}
