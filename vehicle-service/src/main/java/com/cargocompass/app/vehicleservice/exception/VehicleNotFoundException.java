package com.cargocompass.app.vehicleservice.exception;

import org.springframework.http.HttpStatus;

public class VehicleNotFoundException extends ApiException {
    public VehicleNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}