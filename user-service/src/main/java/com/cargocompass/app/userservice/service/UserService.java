package com.cargocompass.app.userservice.service;
import com.cargocompass.app.userservice.dto.UserDto;
import com.cargocompass.app.userservice.request.ChangePasswordRequest;
import com.cargocompass.app.userservice.request.CreateUserRequest;
import com.cargocompass.app.userservice.request.UpdatUserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserDto updateUser(UpdatUserRequest request, Long userId);
    void changePassword(ChangePasswordRequest changePasswordRequest, String email);
    ResponseEntity<?> saveUser(CreateUserRequest request);
    void sendForgotPasswordMail(String email);
    boolean resetUserPassword(String token, String newPassword) ;
    UserDto getUserById(Long id);
    }
