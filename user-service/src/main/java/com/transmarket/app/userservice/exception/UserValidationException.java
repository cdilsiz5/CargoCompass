package com.transmarket.app.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserValidationException {
    private Map<String, String> validationErrors;
    private int statusCode;
    private String exceptionType;
    private LocalDateTime errorTime;
}
