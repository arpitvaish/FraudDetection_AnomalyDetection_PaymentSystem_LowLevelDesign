package com.paymentsystem.lld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Transaction Type Not supported")
public class TransactionNotFoundException extends RuntimeException {
}
