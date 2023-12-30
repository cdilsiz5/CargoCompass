package com.transmarket.app.vehicleservice.service.impl;

 
import com.transmarket.app.vehicleservice.dto.VehicleDto;
import com.transmarket.app.vehicleservice.exception.VehicleNotFoundException;
import com.transmarket.app.vehicleservice.model.Vehicle;
import com.transmarket.app.vehicleservice.repository.VehicleRepository;
import com.transmarket.app.vehicleservice.request.CreateVehicleRequest;
import com.transmarket.app.vehicleservice.request.UpdateVehicleRequest;
import com.transmarket.app.vehicleservice.service.IVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.transmarket.app.vehicleservice.constant.VehicleConstant.VEHICLE_NOT_FOUND;
import static com.transmarket.app.vehicleservice.mapper.VehicleMapper.VEHICLE_MAPPER;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements IVehicleService {

    private final VehicleRepository repository;


    @Override
    @Transactional
    public VehicleDto createVehicle(CreateVehicleRequest createRequest) {
        log.info("Creating Vehicle with request: {}", createRequest);
        Vehicle Vehicle = VEHICLE_MAPPER.createVehicle(createRequest);
        return VEHICLE_MAPPER.toVehicleDto( repository.save(Vehicle));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDto getVehicleById(Long id) {
        log.info("Fetching Vehicle with id: {}", id);
        Vehicle Vehicle = repository.findById(id).orElseThrow(() -> {
            log.error("Vehicle not found with id: {}", id);
            return new VehicleNotFoundException(VEHICLE_NOT_FOUND);
        });
        return VEHICLE_MAPPER.toVehicleDto(Vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDto> getAllVehicles() {
        log.info("Fetching all Vehicles");
        return VEHICLE_MAPPER.toVehicleDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public VehicleDto updateVehicle(Long id, UpdateVehicleRequest updateRequest) {
        log.info("Updating Vehicle with id: {}", id);
        Vehicle Vehicle = repository.findById(id).orElseThrow(() -> {
            log.error("Vehicle not found with id: {}", id);
            return new VehicleNotFoundException(VEHICLE_NOT_FOUND);
        });
         Vehicle=checkUpdateRequest(Vehicle,updateRequest);
         Vehicle updatedVehicle=repository.save(Vehicle);
        log.info("Updated Vehicle with id: {}", updatedVehicle.getId());
        return VEHICLE_MAPPER.toVehicleDto(updatedVehicle);
    }



    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        repository.findById(id).orElseThrow(()-> {
            log.error("Vehicle not found with id: {}", id);
            return new VehicleNotFoundException(VEHICLE_NOT_FOUND);
        });
        repository.deleteById(id);
    }



    private Vehicle checkUpdateRequest(Vehicle Vehicle, UpdateVehicleRequest request) {
            if (request.getLicensePlate() != null && !request.getLicensePlate().isEmpty()) {
                Vehicle.setLicensePlate(request.getLicensePlate());
            }
            return Vehicle;
        }

    }

