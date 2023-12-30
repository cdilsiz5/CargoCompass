package com.transmarket.app.cargoservice.exception;

import org.springframework.http.HttpStatus;

public class BussinessException extends ApiException {
    public BussinessException (String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
