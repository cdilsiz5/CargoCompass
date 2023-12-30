package com.cargocompass.app.offerservice.request;

import com.cargocompass.app.offerservice.model.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOfferRequest {

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Status cannot be null")
    private OfferStatus status;

    private String notes;

    @NotNull(message = "Offer date cannot be null")
    @Builder.Default
    private LocalDate offerDate=LocalDate.now();

}