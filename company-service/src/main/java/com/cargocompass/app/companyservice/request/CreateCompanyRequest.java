package com.cargocompass.app.companyservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompanyRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Registration number cannot be blank")
    @Size(min = 1, max = 20, message = "Registration number must be between 1 and 20 characters")
    private String registrationNumber;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number is not valid")
    private String phoneNumber;

    @NotNull(message = "Establishment date cannot be null")
    @PastOrPresent(message = "Establishment date must be in the past or present")
    private Date establishmentDate;

    @NotNull(message = "User ID cannot be null")
    private Long userId;


}
