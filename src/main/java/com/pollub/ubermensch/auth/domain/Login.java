package com.pollub.ubermensch.auth.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Login {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

