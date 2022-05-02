package com.paymentsystem.lld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Insufficient Funds")
public class LowFundsException extends RuntimeException {
}
