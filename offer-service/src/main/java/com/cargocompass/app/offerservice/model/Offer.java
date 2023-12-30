package com.cargocompass.app.offerservice.model;
import com.cargocompass.app.offerservice.model.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.cargocompass.app.offerservice.model.enums.OfferStatus.PENDING;

@Entity
@Table(name = "offers")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cargoId; // Teklifin yapıldığı yük ilanının ID'si

    @Column(nullable = false)
    private Long companyId; // Teklifi yapan şirketin ID'si

    @Column(nullable = false)
    private Long userId; // Teklifi yapan kullanıcının ID'si

    @Column(nullable = false)
    private BigDecimal amount; // Teklifin miktari

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OfferStatus status = PENDING; // Teklifin durumu, örneğin: PENDING, ACCEPTED, REJECTED

    @Column
    private String notes; // Teklifle ilgili notlar

    @Builder.Default
    private LocalDate offerDate=LocalDate.now();
}