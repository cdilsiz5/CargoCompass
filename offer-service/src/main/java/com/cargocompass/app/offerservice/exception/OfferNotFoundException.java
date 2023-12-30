package com.cargocompass.app.offerservice.exception;

import org.springframework.http.HttpStatus;

public class OfferNotFoundException extends ApiException {
    public OfferNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
