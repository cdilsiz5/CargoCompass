package com.cargocompass.app.userservice.request;

import com.cargocompass.app.userservice.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserRequest {
    @NotBlank(message ="First Name Cannot Be Null")
    private String userFirstName;


    @NotBlank(message ="Last Name Cannot Be Null")
    private String userLastName;


    @NotBlank(message ="Email Cannot Be Null")
    @Email(message = "Please Enter Email Format")
    private String userEmail;

    @NotBlank(message ="Phone Number Cannot Be Null")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",message = "Please Enter Phone Number In Format")
    private String userPhoneNumber;

    @NotBlank(message ="Password Cannot Be Null")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message="Minimum eight characters, at least one uppercase letter, one lowercase letter, one number ")
    private String userPassword;

     @Enumerated(EnumType.STRING)
     private UserRole userRole;

}
