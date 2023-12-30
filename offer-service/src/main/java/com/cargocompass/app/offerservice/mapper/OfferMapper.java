package com.cargocompass.app.offerservice.mapper;

import com.cargocompass.app.offerservice.dto.OfferDto;
import com.cargocompass.app.offerservice.model.Offer;
import com.cargocompass.app.offerservice.request.CreateOfferRequest;
import com.cargocompass.app.offerservice.request.UpdateOfferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferMapper OFFER_MAPPER    = Mappers.getMapper(OfferMapper.class);

    OfferDto toOfferDto(Offer cargo);
    List<OfferDto> toOfferDtoList(List<Offer> offerList);
    Offer createOffer(CreateOfferRequest request);
    void updateOffer(@MappingTarget Offer offer, UpdateOfferRequest request);

}

