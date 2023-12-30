package com.transmarket.app.userservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    @NotBlank(message ="Current Password Cannot Be Null")
    private String currentPassword;

    @NotBlank(message ="Password Cannot Be Null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message="Your password must be at least 8 characters long and include a mix of upper and lower case letters, numbers, and symbols (such as @, $, !, %, *, ?, &).")
    private String newPassword;
}
