package com.transmarket.app.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "refresh_token")
public class RefreshToken extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",insertable = false,updatable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}