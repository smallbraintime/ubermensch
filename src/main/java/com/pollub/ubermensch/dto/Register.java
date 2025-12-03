package com.pollub.ubermensch.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Register {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Surname is required.")
    private String surname;
    @NotBlank(message = "Email is required.")
    @Email(message = "Must be a valid email format.")
    private String email;
    @NotBlank(message = "Phone number is required.")
    @Size(min = 9, message = "Phone number must be 9 characters.")
    private String phoneNumber;
    @NotBlank(message = "Country is required.")
    private String country;
    @NotBlank(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters.")
    private String password;
}