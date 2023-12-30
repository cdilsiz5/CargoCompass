package com.transmarket.app.cargoservice.mapper;

import com.transmarket.app.cargoservice.dto.CargoDto;
import com.transmarket.app.cargoservice.model.Cargo;
import com.transmarket.app.cargoservice.request.CreateCargoRequest;
import com.transmarket.app.cargoservice.request.UpdateCargoRequest;
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
