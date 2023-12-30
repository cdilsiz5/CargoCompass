package com.transmarket.app.offerservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferValidationException {
    private Map<String, String> validationErrors;
    private int statusCode;
    private String exceptionType;
    private LocalDateTime errorTime;
}
