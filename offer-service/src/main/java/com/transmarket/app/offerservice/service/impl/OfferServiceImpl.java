package com.transmarket.app.offerservice.service.impl;

import com.transmarket.app.offerservice.dto.OfferDto;
import com.transmarket.app.offerservice.exception.OfferNotFoundException;
import com.transmarket.app.offerservice.model.Offer;
import com.transmarket.app.offerservice.model.enums.OfferStatus;
import com.transmarket.app.offerservice.repository.OfferRepository;
import com.transmarket.app.offerservice.request.CreateOfferRequest;
import com.transmarket.app.offerservice.request.UpdateOfferRequest;
import com.transmarket.app.offerservice.service.IOfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.transmarket.app.offerservice.constant.OfferConstant.OFFER_NOT_FOUND;
import static com.transmarket.app.offerservice.mapper.OfferMapper.OFFER_MAPPER;
import static com.transmarket.app.offerservice.model.enums.OfferStatus.PENDING;

@Service
@Slf4j
@RequiredArgsConstructor
public class OfferServiceImpl implements IOfferService {

    private final OfferRepository offerRepository;
    @Override
    @Transactional
    public OfferDto createOffer(CreateOfferRequest request) {
        log.info("Creating offer with request: {}", request);
        Offer offer=OFFER_MAPPER.createOffer(request);
        return OFFER_MAPPER.toOfferDto(offerRepository.save(offer));
        // todo: send user mail for offer

    }

    @Override
    @Transactional(readOnly = true)
    public OfferDto getOfferById(Long offerId) {
        log.info("Fetching offer with id : {}",offerId);
        Offer offer=findOfferById(offerId);
        return OFFER_MAPPER.toOfferDto(offer);

    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferDto> getAllOffers() {
        log.info("Fetching all offers");
        return OFFER_MAPPER.toOfferDtoList(offerRepository.findAll()); 
    }

    @Override
    @Transactional
    public OfferDto updateOffer(Long offerId, UpdateOfferRequest request) {
        log.info("Updating offer with id : {}",offerId);
        Offer offer=findOfferById(offerId);
        offer=checkUpdateRequest(offer,request);
        Offer updatedOffer=offerRepository.save(offer);
        log.info("Updated Offer with id : {}",offerId);
        return OFFER_MAPPER.toOfferDto(updatedOffer);
    }

    @Override
    @Transactional
    public OfferDto updateOfferStatus(Long offerId, OfferStatus status) {
        checkOfferExistById(offerId);
        if(offerRepository.updateOfferStatus(offerId,status)==1){
            log.info("Updating status of cargo with id: {} to {}", offerId,status);
            OfferDto updatedOffer=OFFER_MAPPER.toOfferDto(findOfferById(offerId));
            log.info("Updated offer status with offer id : {}",offerId);
            if(status==OfferStatus.ACCEPTED){
                //todo: will  create tracking cargo service for this cargo ;
            }
            return updatedOffer;
        }else{
            log.error("Failed to update status of offer with id: {}", offerId);
            throw new RuntimeException("Status could not be updated");
        }
    }


    @Override
    @Transactional
    public void deleteOffer(Long offerId) {
        log.info("Deleting offer with id : {}",offerId);
        Offer offer=offerRepository.findById(offerId).orElseThrow(()->{
            log.error("Offer not found with id: {}",offerId);
            return new OfferNotFoundException(OFFER_NOT_FOUND);
        });
        offerRepository.deleteById(offerId);
        log.info("Deleted Offer with id : {}",offerId);
    }


    private Offer checkUpdateRequest(Offer offer, UpdateOfferRequest request) {
        if (request.getAmount() != null && request.getAmount().compareTo(BigDecimal.ZERO)>1) {
            offer.setAmount(request.getAmount());
        }
        if (request.getNotes() != null && request.getNotes().isEmpty()) {
            offer.setNotes(request.getNotes());
        }
        offer.setOfferDate(LocalDate.now());
        offer.setStatus(PENDING);
        return offer;
    }
    private void checkOfferExistById(Long offerId) {
        offerRepository.findById(offerId).orElseThrow(()->{
            log.error("Offer not found with id: {}", offerId);
            return new OfferNotFoundException(OFFER_NOT_FOUND);
        });
    }
    private Offer findOfferById(Long offerId) {
        Offer offer=offerRepository.findById(offerId).orElseThrow(()->{
            log.error("Offer not found with id: {}", offerId);
            return new OfferNotFoundException(OFFER_NOT_FOUND);
        });
        return offer;
    }

}
