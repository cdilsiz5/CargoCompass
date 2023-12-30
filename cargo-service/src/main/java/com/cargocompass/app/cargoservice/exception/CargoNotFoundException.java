package com.cargocompass.app.cargoservice.exception;

import org.springframework.http.HttpStatus;

public class CargoNotFoundException  extends ApiException {
    public CargoNotFoundException (String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}