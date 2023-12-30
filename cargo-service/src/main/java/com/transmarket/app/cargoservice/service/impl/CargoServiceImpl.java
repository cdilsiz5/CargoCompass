package com.transmarket.app.cargoservice.service.impl;

import com.transmarket.app.cargoservice.dto.CargoDto;
import com.transmarket.app.cargoservice.exception.BussinessException;
import com.transmarket.app.cargoservice.model.Cargo;
import com.transmarket.app.cargoservice.exception.CargoNotFoundException;
import com.transmarket.app.cargoservice.model.enums.CargoStatus;
import com.transmarket.app.cargoservice.repository.CargoRepository;
import com.transmarket.app.cargoservice.request.CreateCargoRequest;
import com.transmarket.app.cargoservice.request.UpdateCargoRequest;
import com.transmarket.app.cargoservice.service.ICargoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.transmarket.app.cargoservice.constant.CargoConstant.CARGO_NOT_FOUND;
import static com.transmarket.app.cargoservice.mapper.CargoMapper.CARGO_MAPPER;

@Service
@RequiredArgsConstructor
@Slf4j
public class CargoServiceImpl implements ICargoService {

    private final CargoRepository repository;


    @Override
    @Transactional
    public CargoDto createCargo(CreateCargoRequest createRequest) {
        log.info("Creating cargo with request: {}", createRequest);
        Cargo cargo = CARGO_MAPPER.createCargo(createRequest);
        return CARGO_MAPPER.toCargoDto( repository.save(cargo));
    }

    @Override
    @Transactional(readOnly = true)
    public CargoDto getCargoById(Long id) {
        log.info("Fetching cargo with id: {}", id);
        Cargo cargo = repository.findById(id).orElseThrow(() -> {
            log.error("Cargo not found with id: {}", id);
            return new CargoNotFoundException(CARGO_NOT_FOUND);
        });
        return CARGO_MAPPER.toCargoDto(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CargoDto> getAllCargos() {
        log.info("Fetching all cargos");
        return CARGO_MAPPER.toCargoDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public CargoDto updateCargo(Long id, UpdateCargoRequest updateRequest) {
        log.info("Updating cargo with id: {}", id);
        Cargo cargo = repository.findById(id).orElseThrow(() -> {
            log.error("Cargo not found with id: {}", id);
            return new CargoNotFoundException(CARGO_NOT_FOUND);
        });
         cargo=checkUpdateRequest(cargo,updateRequest);
         Cargo updatedCargo=repository.save(cargo);
        log.info("Updated cargo with id: {}", updatedCargo.getId());
        return CARGO_MAPPER.toCargoDto(updatedCargo);
    }



    @Override
    @Transactional
    public void deleteCargo(Long id) {
        repository.findById(id).orElseThrow(()-> {
            log.error("Cargo not found with id: {}", id);
            return new CargoNotFoundException(CARGO_NOT_FOUND);
        });
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public CargoDto updateCargoStatus(Long id, CargoStatus newStatus) {
        Cargo cargo=repository.findById(id).orElseThrow(()-> {
            log.error("Cargo not found with id: {}", id);
            return new CargoNotFoundException(CARGO_NOT_FOUND);
        });
        if (repository.updateCargoStatus(id, newStatus)== 1) {
            log.info("Updating status of cargo with id: {} to {}", id, newStatus);
            CargoDto updatedCargo = CARGO_MAPPER.toCargoDto(repository.findById(id).orElseThrow(()->new CargoNotFoundException(CARGO_NOT_FOUND)));
            log.info("Updated status of cargo with id: {}", id);
            return updatedCargo;
        } else {
            log.error("Failed to update status of cargo with id: {}", id);
            throw new BussinessException("Status could not be updated");
        }
    }

    @Override
    @Transactional
    public List<CargoDto> getCargosByStatus(CargoStatus status) {
        log.info("Fetching cargos with status: {}", status);
        return CARGO_MAPPER.toCargoDtoList(repository.findByStatus(status));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CargoDto> getCargosByOriginAndDestination(String origin, String destination) {
        log.info("Fetching cargos from {} to {}", origin, destination);
        return CARGO_MAPPER.toCargoDtoList(repository.findByOriginAndDestination(origin, destination));
    }

    @Override
    @Transactional
    public CargoDto assignCarrierToCargo(Long cargoId, Long carrierId) {
        // TODO: Implement this method
        log.info("Assigning carrier with id: {} to cargo with id: {}", carrierId, cargoId);
        return null;
    }

    @Override
    @Transactional
    public void cancelCargo(Long id) {
        log.info("Cancelling cargo with id: {}", id);
        // TODO: Implement this method
    }
    private Cargo checkUpdateRequest(Cargo cargo, UpdateCargoRequest request) {
            if (request.getDescription() != null && !request.getDescription().isEmpty()) {
                cargo.setDescription(request.getDescription());
            }
            if (request.getWeight() != null && request.getWeight() > 0) {
                cargo.setWeight(request.getWeight());
            }
            if (request.getDimensions() != null && !request.getDimensions().isEmpty()) {
                cargo.setDimensions(request.getDimensions());
            }
            if (request.getTransportType() != null) {
                cargo.setTransportType(request.getTransportType());
            }
            if (request.getOrigin() != null && !request.getOrigin().isEmpty()) {
                cargo.setOrigin(request.getOrigin());
            }
            if (request.getDestination() != null && !request.getDestination().isEmpty()) {
                cargo.setDestination(request.getDestination());
            }
            return cargo;
        }

    }

