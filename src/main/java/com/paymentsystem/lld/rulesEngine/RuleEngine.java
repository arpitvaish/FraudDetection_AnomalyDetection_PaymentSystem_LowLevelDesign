package com.paymentsystem.lld.rulesEngine;

import com.paymentsystem.lld.exception.NoMatchingRuleFoundException;
import com.paymentsystem.lld.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    List<IRule<Transaction, Transaction>> rules;

    public RuleEngine() {
        rules = new ArrayList<>();
    }

    public Transaction rule(Transaction transaction) {
        return rules.stream()
                .filter(rule -> rule.isApplicable(transaction))
                .map(rule -> rule.execute(transaction))
                .findFirst()
                .orElseThrow(() -> new NoMatchingRuleFoundException("No Matching rule found"));
    }


    public RuleEngine registerRule(IRule<Transaction, Transaction> rule) {
        rules.add(rule);
        return this;
    }
}
