package com.cargocompass.app.cargoservice.request;

import com.transmarket.app.commondata.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCargoRequest {

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "User id  cannot be null")
    @Min(value = 1, message = "User id must be greater than 0")
    private Long userId;

    @NotNull(message = "Weight cannot be null")
    @Min(value = 1, message = "Weight must be greater than 0")
    private Double weight;

    @NotBlank(message = "Dimensions cannot be blank")
    private String dimensions;

    @NotNull(message = "Transport type cannot be null")
    private TransportType transportType;

    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotNull(message = "Estimated departure date cannot be null")
    private LocalDate estimatedDepartureDate;

    @NotNull(message = "Estimated arrival date cannot be null")
    private LocalDate estimatedArrivalDate;

}
