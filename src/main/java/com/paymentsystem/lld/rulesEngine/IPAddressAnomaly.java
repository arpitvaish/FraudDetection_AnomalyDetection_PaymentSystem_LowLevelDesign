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
        return null;
    }
}
