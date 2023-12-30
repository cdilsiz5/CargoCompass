package com.transmarket.app.offerservice.repository;

import com.transmarket.app.offerservice.model.Offer;
import com.transmarket.app.offerservice.model.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Offer o SET o.status = :status WHERE o.id = :id")
    int updateOfferStatus(@Param("id") Long id, @Param("status") OfferStatus status);

}
