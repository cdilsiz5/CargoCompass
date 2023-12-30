package com.cargocompass.app.cargoservice.model;

 import com.cargocompass.app.cargoservice.model.enums.CargoStatus;
 import com.transmarket.app.commondata.enums.TransportType;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Data;
 import lombok.NoArgsConstructor;
 import lombok.experimental.SuperBuilder;

 import javax.persistence.*;
 import java.time.LocalDate;

 import static com.cargocompass.app.cargoservice.model.enums.CargoStatus.AWAITING_PICKUP;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cargos")
@SuperBuilder
public class Cargo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private double weight; // kg cinsinden

    @Column(nullable = false)
    private String dimensions; // Örneğin: "2.5m x 10m x 3m" (Genişlik x Uzunluk x Yükseklik)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportType transportType; // Örneğin: "Tır"

    @Column(nullable = false)
    private String origin; // Kalkış noktası, örneğin "Mersin, Türkiye"

    @Column(nullable = false)
    private String destination; // Varış noktası, örneğin "Paris, Fransa"

    private LocalDate estimatedDepartureDate;


    private LocalDate estimatedArrivalDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CargoStatus status =AWAITING_PICKUP;



}
