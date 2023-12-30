package com.transmarket.app.cargoservice.dto;

import com.transmarket.app.commondata.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CargoDto {


    private Long id;

    private String description;

    private Long userId;

    private double weight;

    private String dimensions;

    private TransportType transportType;

    private String origin; // Kalkış noktası, örneğin "Mersin, Türkiye"

    private String destination; // Varış noktası, örneğin "Paris, Fransa"


    private LocalDate estimatedDepartureDate;


    private LocalDate estimatedArrivalDate;

    private String status;


}
