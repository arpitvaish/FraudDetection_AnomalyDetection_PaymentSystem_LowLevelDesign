package com.paymentsystem.lld.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Account {

    private String id;
    private Customer customer;
    private Double withDrawLimit;
    private Double availableBalance;
}
