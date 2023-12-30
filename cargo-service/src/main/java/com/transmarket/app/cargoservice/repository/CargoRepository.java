package com.transmarket.app.cargoservice.repository;

import com.transmarket.app.cargoservice.model.Cargo;
import com.transmarket.app.cargoservice.model.enums.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Cargo c SET c.status = :status WHERE c.id = :id")
    int updateCargoStatus(@Param("id") Long id, @Param("status") CargoStatus status);



    List<Cargo> findByStatus(CargoStatus status);

    List<Cargo> findByOriginAndDestination(String origin,String destination);

}
