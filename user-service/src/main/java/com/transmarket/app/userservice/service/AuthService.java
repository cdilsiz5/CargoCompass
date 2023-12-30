package com.transmarket.app.userservice.service;
import com.transmarket.app.userservice.request.CreateUserRequest;
import com.transmarket.app.userservice.request.LoginRequest;
import com.transmarket.app.userservice.request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> Login(LoginRequest request);
    ResponseEntity<?> logoutUser() ;
    ResponseEntity<?> refreshtoken(TokenRefreshRequest request) ;


    }
