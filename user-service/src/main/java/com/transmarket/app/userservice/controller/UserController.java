package com.transmarket.app.userservice.controller;

import com.transmarket.app.userservice.dto.UserDto;
import com.transmarket.app.userservice.request.ChangePasswordRequest;
import com.transmarket.app.userservice.request.CreateUserRequest;
import com.transmarket.app.userservice.request.ResetPasswordRequest;
import com.transmarket.app.userservice.request.UpdatUserRequest;
import com.transmarket.app.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.transmarket.app.userservice.constants.UserConstants.*;
@Tag(
        name = "CRUD REST APIs for User in Trans Market",
        description = "CRUD REST APIs in Trans Market to CREATE, UPDATE, FETCH AND DELETE account details"
)
@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = API_PREFIX + API_VERSION_V1 + API_USER)
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "REST API to create new User inside Trans Market"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class),
                            mediaType = "application/json")))
    @PostMapping(API_USER_REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest user) {
        return userService.saveUser(user);
    }

    @Operation(
            summary = "Update User Details REST API",
            description = "REST API to update User details based on cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class),
                            mediaType = "application/json")))
    @PutMapping(API_USER_UPDATE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@Valid @RequestBody UpdatUserRequest request,@PathVariable Long userId){
        return userService.updateUser(request, userId);
    }

    @Operation(
            summary = "Fetch User Details REST API",
            description = "REST API to fetch User details based on a cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class),
                            mediaType = "application/json")))
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getByUserId(@PathVariable Long userId){
        return userService.getUserById( userId);
    }
    @Operation(
            summary = "Change User Password REST API",
            description = "REST API toChange User Password based on User Email"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ResponseEntity.class),
                            mediaType = "application/json")))
    @CrossOrigin("http://localhost:3000")
    @PutMapping(API_USER_CHANGE_PASSWORD)
    public ResponseEntity changePassword(@Valid @RequestBody ChangePasswordRequest request ,@RequestHeader String email) {
        userService.changePassword(request,email);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Send User Forgot Password Link REST API",
            description = "REST API to Send User Forgot Password Link "
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ResponseEntity.class),
                            mediaType = "application/json")))
    @GetMapping(API_USER_FORGOT)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("http://localhost:3000")
    public void sendForgetPasswordLink(@RequestParam String email) {
        userService.sendForgotPasswordMail(email);
    }
    @Operation(
            summary = "Send User Reset Password REST API",
            description = "REST API to Reset User Password  "
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = ResponseEntity.class),
                            mediaType = "application/json")))
    @CrossOrigin("http://localhost:3000")
    @PostMapping(API_USER_RESET)

    public ResponseEntity resetForgottenPassword(@Valid @RequestBody ResetPasswordRequest request) {
        boolean reset = userService.resetUserPassword(request.getToken(), request.getNewPassword());
        return reset ? ResponseEntity.ok("Password has been reset successfully.")
                : ResponseEntity.badRequest().body("Invalid or expired token.");
    }
}

