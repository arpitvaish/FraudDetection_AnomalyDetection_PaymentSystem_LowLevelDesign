package com.paymentsystem.lld.rulesEngine;

public interface IRule<K,V> {

    public boolean isApplicable(K input);
    public V execute(K input);
}
