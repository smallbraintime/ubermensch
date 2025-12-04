package com.pollub.ubermensch.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Register {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email(message = "Must be a valid email format.")
    private String email;

    @NotBlank
    @Size(min = 9, message = "Phone number must be 9 characters.")
    private String phoneNumber;

    @NotBlank
    private String country;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters.")
    private String password;
}