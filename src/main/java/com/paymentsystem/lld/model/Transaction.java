package com.paymentsystem.lld.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Transaction implements Serializable {

    private String id;
    private TransactionType transactionType;
    private Account account;
    private Date transactionTime;
    private Browser browser;
    private String timeZone;
    private String ipaddress;
    private Double amount;
    private String country;
    private boolean isFraud;
    private String fraudType;
    private boolean isAnomaly;
    private String anomalyType;
}
