package com.cargocompass.app.offerservice.dto;

import com.cargocompass.app.offerservice.model.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {
    private Long id;

    private Long cargoId;

    private Long companyId;

    private Long userId;

    private BigDecimal amount;

    private OfferStatus status ;

    private String notes;

    private LocalDate offerDate;
}
