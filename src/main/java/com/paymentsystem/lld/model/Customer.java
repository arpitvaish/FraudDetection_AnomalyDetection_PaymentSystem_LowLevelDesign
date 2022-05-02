package com.paymentsystem.lld.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Customer implements Serializable {

    private String id;
    private String name;
    private String city;
    private String country;
    private String dob;
    private String fatherName;
    private String motherName;
    private KYCType kycType;
    private String kycNumber;
}
