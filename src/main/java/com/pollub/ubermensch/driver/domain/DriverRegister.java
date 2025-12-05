package com.pollub.ubermensch.driver.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DriverRegister {
    @NotBlank
    private String licenseNo;

    @Valid
    private Vehicle vehicle;
}
