package com.transmarket.app.vehicleservice.service;
import com.transmarket.app.vehicleservice.dto.VehicleDto;
 import com.transmarket.app.vehicleservice.request.CreateVehicleRequest;
import com.transmarket.app.vehicleservice.request.UpdateVehicleRequest;

import java.util.List;
public interface IVehicleService {

    /**
     * Creates a new Vehicle.
     *
     * @param createRequest the request data for creating a new Vehicle
     * @return the DTO of the created Vehicle
     */
    VehicleDto createVehicle(CreateVehicleRequest createRequest);

    /**
     * Retrieves a Vehicle by its ID.
     *
     * @param id the ID of the Vehicle
     * @return the DTO of the corresponding Vehicle
     */
    VehicleDto getVehicleById(Long id);

    /**
     * Lists all Vehicles.
     *
     * @return a list of all Vehicles
     */
    List<VehicleDto> getAllVehicles();

    /**
     * Updates a specific Vehicle.
     *
     * @param id the ID of the Vehicle to update
     * @param updateRequest the update request data for the Vehicle
     * @return the DTO of the updated Vehicle
     */
    VehicleDto updateVehicle(Long id, UpdateVehicleRequest updateRequest);

    /**
     * Deletes a specific Vehicle.
     *
     * @param id the ID of the Vehicle to be deleted
     */
    void deleteVehicle(Long id);


}