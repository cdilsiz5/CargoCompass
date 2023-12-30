package com.cargocompass.app.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDto {

    private Long id;

    private Long companyId;

    private String licensePlate;

    private String vehicleType;

    private String brand;

    private String model;

    private Integer year;

}
