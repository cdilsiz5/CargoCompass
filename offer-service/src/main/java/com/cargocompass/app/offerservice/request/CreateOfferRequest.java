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
public class CreateOfferRequest {

    @NotNull(message = "Cargo ID cannot be null")
    private Long cargoId;

    @NotNull(message = "Company ID cannot be null")
    private Long companyId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

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