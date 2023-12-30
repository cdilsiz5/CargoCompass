package com.transmarket.app.userservice.exception;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}