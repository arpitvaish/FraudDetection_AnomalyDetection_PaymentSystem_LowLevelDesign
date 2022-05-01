package com.paymentsystem.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {

    private String id;
    private Customer customer;
    private Double withDrawLimit;
    private Double availableBalance;
}
