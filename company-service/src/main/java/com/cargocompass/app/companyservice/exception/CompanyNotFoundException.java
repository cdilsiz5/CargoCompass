package com.cargocompass.app.companyservice.exception;

import org.springframework.http.HttpStatus;

public class CompanyNotFoundException extends ApiException {
    public CompanyNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

