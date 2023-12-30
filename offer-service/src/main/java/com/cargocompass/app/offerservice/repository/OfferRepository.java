package com.cargocompass.app.offerservice.repository;

import com.cargocompass.app.offerservice.model.enums.OfferStatus;
import com.cargocompass.app.offerservice.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OfferRepository extends JpaRepository<com.cargocompass.app.offerservice.model.Offer,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Offer o SET o.status = :status WHERE o.id = :id")
    int updateOfferStatus(@Param("id") Long id, @Param("status") OfferStatus status);

}
