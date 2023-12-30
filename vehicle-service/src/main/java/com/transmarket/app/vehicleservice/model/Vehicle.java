package com.transmarket.app.vehicleservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "vehicles")
public class Vehicle extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

}
