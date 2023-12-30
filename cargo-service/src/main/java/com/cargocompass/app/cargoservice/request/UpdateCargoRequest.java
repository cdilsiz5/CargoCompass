package com.cargocompass.app.cargoservice.request;

import com.transmarket.app.commondata.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCargoRequest {
    private String description;

    @Min(value = 1, message = "Weight must be greater than 0")
    private Double weight;

    private String dimensions;

    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    private String origin;

    private String destination;


}
