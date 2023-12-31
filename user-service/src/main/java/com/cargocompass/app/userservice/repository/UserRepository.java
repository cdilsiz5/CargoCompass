package com.cargocompass.app.userservice.repository;

import com.cargocompass.app.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserEmail(String email);

    boolean existsByUserEmail(String email);

}
