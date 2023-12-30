package com.cargocompass.app.offerservice.service;


import com.cargocompass.app.offerservice.dto.OfferDto;
import com.cargocompass.app.offerservice.model.enums.OfferStatus;
import com.cargocompass.app.offerservice.request.CreateOfferRequest;
import com.cargocompass.app.offerservice.request.UpdateOfferRequest;

import java.util.List;

public interface IOfferService {

    /**
     * Creates a new offer.
     *
     * @param request the request data for the new offer
     * @return the DTO of the created offer
     */
    OfferDto createOffer(CreateOfferRequest request);

    /**
     * Retrieves an offer by its ID.
     *
     * @param offerId the ID of the offer
     * @return the DTO of the corresponding offer
     */
    OfferDto getOfferById(Long offerId);

    /**
     * Lists all offers.
     *
     * @return a list of all offers
     */
    List<OfferDto> getAllOffers();

    /**
     * Updates a specific offer.
     *
     * @param offerId the ID of the offer to update
     * @param request the update request data for the offer
     * @return the DTO of the updated offer
     */
    OfferDto updateOffer(Long offerId, UpdateOfferRequest request);

    /**
     * Updates a specific offer.
     *
     * @param offerId the ID of the offer to update
     * @param status the update request data for the offer
     * @return the DTO of the updated offer
     */
    OfferDto updateOfferStatus(Long offerId, OfferStatus status);


    /**
     * Deletes a specific offer.
     *
     * @param offerId the ID of the offer to be deleted
     */
    void deleteOffer(Long offerId);


}
