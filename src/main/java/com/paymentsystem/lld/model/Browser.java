package com.paymentsystem.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Browser {

    private BrowserType browserType;
    private boolean isNoTrackingEnabled;
    private double browserWidth;
    private double browserLength;
}
