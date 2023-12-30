package com.cargocompass.app.userservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {



    @NotBlank(message ="Email Cannot Be Null")
    @Email(message = "Please Enter Email Format")
    private String userEmail;

    @NotBlank(message ="Password Cannot Be Null")
    private String userPassword;
}
