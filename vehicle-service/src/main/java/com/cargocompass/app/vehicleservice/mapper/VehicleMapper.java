package com.cargocompass.app.vehicleservice.mapper;


import com.cargocompass.app.vehicleservice.request.CreateVehicleRequest;
import com.cargocompass.app.vehicleservice.request.UpdateVehicleRequest;
import com.cargocompass.app.vehicleservice.dto.VehicleDto;
import com.cargocompass.app.vehicleservice.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleMapper VEHICLE_MAPPER= Mappers.getMapper(VehicleMapper.class);

    VehicleDto toVehicleDto(Vehicle vehicle);

    List<VehicleDto> toVehicleDtoList(List<Vehicle> vehicleList);

    Vehicle createVehicle(CreateVehicleRequest request);

    void updateVehicle(@MappingTarget Vehicle vehicle, UpdateVehicleRequest request);
}
