package com.paymentsystem.lld.service;

import com.paymentsystem.lld.model.Transaction;
import com.paymentsystem.lld.rulesEngine.BrowserAnomaly;
import com.paymentsystem.lld.rulesEngine.IPAddressAnomaly;
import com.paymentsystem.lld.rulesEngine.PasswordResetAnomaly;
import com.paymentsystem.lld.rulesEngine.RuleEngine;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnomalyDetectionService {

        RuleEngine ruleEngine;
        public AnomalyDetectionService(RuleEngine ruleEngine){
            this.ruleEngine = ruleEngine;
        }


        public Transaction apply(Transaction transaction){
            return ruleEngine.rule(transaction);
        }
}
