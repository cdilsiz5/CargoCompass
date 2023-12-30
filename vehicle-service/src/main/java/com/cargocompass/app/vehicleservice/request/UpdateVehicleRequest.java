package com.cargocompass.app.vehicleservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateVehicleRequest {
    @NotBlank(message = "License plate cannot be blank")
    @Pattern(regexp = "[A-Z0-9-]+", message = "License plate must contain only uppercase letters, numbers, and hyphens")
    private String licensePlate;
}
