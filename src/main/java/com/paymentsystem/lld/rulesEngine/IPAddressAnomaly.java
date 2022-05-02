package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.model.Transaction;

public class IPAddressAnomaly implements IRule<Transaction,Transaction> {
    @Override
    public boolean isApplicable(Transaction input) {
        //input.getIpaddress() != input.getAccount().getCustomer().
        return false;
    }

    @Override
    public Transaction execute(Transaction input) {
        input.setAnomaly(true);
        input.setAnomalyType("IP Address Anomaly");
        return input;
    }
}
