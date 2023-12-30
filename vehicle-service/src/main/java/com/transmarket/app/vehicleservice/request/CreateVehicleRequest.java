package com.transmarket.app.vehicleservice.request;

import com.transmarket.app.commondata.enums.TransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateVehicleRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Company ID cannot be null")
    private Long companyId; // Taşıma şirketinin benzersiz ID'si

    @NotBlank(message = "License plate cannot be blank")
    @Pattern(regexp = "[A-Z0-9-]+", message = "License plate must contain only uppercase letters, numbers, and hyphens")
    private String licensePlate; // Araç plakası

    @NotBlank(message = "Vehicle type cannot be blank")
    private String vehicleType; // Araç tipi

    @NotBlank(message = "Brand cannot be blank")
    private String brand; // Araç markası

    @NotBlank(message = "Model cannot be blank")
    private String model; // Araç modeli

    @NotNull(message = "Year cannot be null")
    @Min(value = 1900, message = "Year must be after 1900")
    @Max(value = 2100, message = "Year must be before 2100") // Gelecekteki yılları da hesaba katarak
    private Integer year; // Üretim yılı

}
