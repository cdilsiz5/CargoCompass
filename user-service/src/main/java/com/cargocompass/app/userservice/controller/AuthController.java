package com.cargocompass.app.userservice.controller;
import com.cargocompass.app.userservice.request.LoginRequest;
import com.cargocompass.app.userservice.request.TokenRefreshRequest;
import com.cargocompass.app.userservice.response.JwtResponse;
import com.cargocompass.app.userservice.security.service.JwtService;
import com.cargocompass.app.userservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.cargocompass.app.userservice.constants.UserConstants.API_PREFIX;
import static com.cargocompass.app.userservice.constants.UserConstants.API_VERSION_V1;

@RestController
@RequestMapping(API_PREFIX + API_VERSION_V1+"/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService service;
    private final JwtService jwtService;

    @Operation(
            summary = "Login User ",
            description = "REST API to Login "
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = JwtResponse.class),
                            mediaType = "application/json")))
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return service.Login(loginRequest);
    }
    @Operation(
            summary = "Validate token",
            description = "Rest Api To Validate User Token")
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "201",
            description = "Validate token",
            content = @Content(
                    mediaType = "application/json")))
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/validate")
    public boolean validateToken(@RequestParam("token") String token) {
        return   jwtService.validateToken(token);

    }
    @Operation(
            summary = "Refresh Token Request",
            description = "REST API to get Refresh Token "
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = TokenRefreshRequest.class),
                            mediaType = "application/json")))
    @PostMapping("/refreshtoken")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return service.refreshtoken(request);
    }
    
    public ResponseEntity<?> logoutUser() {
        return service.logoutUser();
    }

}
