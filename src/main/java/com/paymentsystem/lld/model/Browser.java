package com.paymentsystem.lld.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Browser {

    private BrowserType browserType;
    private boolean isNoTrackingEnabled;
    private double browserWidth;
    private double browserLength;
}
