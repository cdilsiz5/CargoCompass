package com.transmarket.app.userservice.exception.handler;

import com.transmarket.app.userservice.exception.ApiException;
import com.transmarket.app.userservice.exception.UserValidationException;
import com.transmarket.app.userservice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ApiException exception){
        ErrorResponse errorResponse= ErrorResponse.builder()
                .message(exception.getMessage())
                .exceptionType(exception.getClass().getSimpleName())
                .statusCode(exception.getHttpStatus().value())
                .errorTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserValidationException>  handleValidationException (MethodArgumentNotValidException exception){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        UserValidationException errorResponse= UserValidationException.builder()
                .exceptionType(exception.getClass().getSimpleName())
                .validationErrors(validationErrors)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
}
