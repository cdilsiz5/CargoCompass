package com.cargocompass.app.cargoservice.service;
import com.cargocompass.app.cargoservice.dto.CargoDto;
import com.cargocompass.app.cargoservice.model.enums.CargoStatus;
import com.cargocompass.app.cargoservice.request.CreateCargoRequest;
import com.cargocompass.app.cargoservice.request.UpdateCargoRequest;

import java.util.List;
public interface ICargoService {

    /**
     * Creates a new cargo.
     *
     * @param createRequest the request data for creating a new cargo
     * @return the DTO of the created cargo
     */
    CargoDto createCargo(CreateCargoRequest createRequest);

    /**
     * Retrieves a cargo by its ID.
     *
     * @param id the ID of the cargo
     * @return the DTO of the corresponding cargo
     */
    CargoDto getCargoById(Long id);

    /**
     * Lists all cargos.
     *
     * @return a list of all cargos
     */
    List<CargoDto> getAllCargos();

    /**
     * Updates a specific cargo.
     *
     * @param id the ID of the cargo to update
     * @param updateRequest the update request data for the cargo
     * @return the DTO of the updated cargo
     */
    CargoDto updateCargo(Long id, UpdateCargoRequest updateRequest);

    /**
     * Deletes a specific cargo.
     *
     * @param id the ID of the cargo to be deleted
     */
    void deleteCargo(Long id);

     /**
     * Updates the status of a specific cargo.
     *
     * @param id the ID of the cargo
     * @param newStatus the new status of the cargo
     * @return the DTO of the cargo with updated status
     */
    CargoDto updateCargoStatus(Long id, CargoStatus newStatus);

    /**
     * Retrieves cargos by their status.
     *
     * @param status the status of the cargos to retrieve
     * @return a list of cargos with the specified status
     */
    List<CargoDto> getCargosByStatus(CargoStatus status);

    /**
     * Retrieves cargos by their origin and destination.
     *
     * @param origin the origin of the cargos
     * @param destination the destination of the cargos
     * @return a list of cargos with the specified origin and destination
     */
    List<CargoDto> getCargosByOriginAndDestination(String origin, String destination);

    /**
     * Assigns a carrier to a cargo.
     *
     * @param cargoId the ID of the cargo
     * @param carrierId the ID of the carrier to assign
     * @return the DTO of the cargo with the assigned carrier
     */
    CargoDto assignCarrierToCargo(Long cargoId, Long carrierId);

    /**
     * Cancels a specific cargo.
     *
     * @param id the ID of the cargo to be cancelled
     */
    void cancelCargo(Long id);
}