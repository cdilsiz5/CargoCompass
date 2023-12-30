package com.cargocompass.app.userservice.service;
import com.cargocompass.app.userservice.request.LoginRequest;
import com.cargocompass.app.userservice.request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> Login(LoginRequest request);
    ResponseEntity<?> logoutUser() ;
    ResponseEntity<?> refreshtoken(TokenRefreshRequest request) ;


    }
