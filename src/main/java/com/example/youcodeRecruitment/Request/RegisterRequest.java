package com.example.youcodeRecruitment.Request;

import com.example.youcodeRecruitment.Utils.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @Size(min = 6, message = "password must be at least 6 characters")
    @NotBlank(message = "password is required")
    private String password;

    private String role;
}