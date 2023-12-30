package com.cargocompass.app.cargoservice.mapper;

import com.cargocompass.app.cargoservice.request.CreateCargoRequest;
import com.cargocompass.app.cargoservice.request.UpdateCargoRequest;
import com.cargocompass.app.cargoservice.dto.CargoDto;
import com.cargocompass.app.cargoservice.model.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    CargoMapper CARGO_MAPPER= Mappers.getMapper(CargoMapper.class);

    CargoDto toCargoDto(Cargo cargp);

    List<CargoDto> toCargoDtoList(List<Cargo> cargoList);

    Cargo createCargo(CreateCargoRequest request);

    void updateCargo(@MappingTarget Cargo cargo, UpdateCargoRequest request);
}
