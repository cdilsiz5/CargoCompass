package com.cargocompass.app.vehicleservice.repository;

import com.cargocompass.app.vehicleservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

}
