package com.paymentsystem.lld.service;

import com.paymentsystem.lld.model.Transaction;
import com.paymentsystem.lld.rulesEngine.RuleEngine;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FraudDetectionService {

    RuleEngine ruleEngine;

    public FraudDetectionService(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }


    public Transaction apply(Transaction transaction) {
        return ruleEngine.rule(transaction);
    }
}
