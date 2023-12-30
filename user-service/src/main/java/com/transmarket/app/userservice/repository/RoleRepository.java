package com.transmarket.app.userservice.repository;

import com.transmarket.app.userservice.model.Role;
import com.transmarket.app.userservice.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByUserRoleEquals(UserRole name);
}
