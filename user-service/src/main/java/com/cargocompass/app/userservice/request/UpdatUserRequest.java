package com.cargocompass.app.userservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatUserRequest {

    @NotBlank(message ="Email Cannot Be Null")
    @Email(message = "Please Enter Email Format")
    private String userEmail;

    @NotBlank(message ="Phone Number Cannot Be Null")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",message = "Please Enter Phone Number In Format")
    private String userPhoneNumber;

    @NotBlank(message ="Password Cannot Be Null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message="Your password must be at least 8 characters long and include a mix of upper and lower case letters, numbers, and symbols (such as @, $, !, %, *, ?, &).")
    private String userPassword;


}
