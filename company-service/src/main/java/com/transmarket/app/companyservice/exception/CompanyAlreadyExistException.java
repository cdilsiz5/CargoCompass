package com.transmarket.app.companyservice.exception;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CompanyAlreadyExistException extends RuntimeException {
    public CompanyAlreadyExistException(String message) {
        super(message);
    }
}