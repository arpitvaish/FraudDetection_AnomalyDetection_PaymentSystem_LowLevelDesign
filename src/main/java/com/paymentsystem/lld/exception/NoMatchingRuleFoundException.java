package com.paymentsystem.lld.exception;

public class NoMatchingRuleFoundException extends RuntimeException{
    public NoMatchingRuleFoundException(String message) {
        throw new RuntimeException(message);
    }
}
