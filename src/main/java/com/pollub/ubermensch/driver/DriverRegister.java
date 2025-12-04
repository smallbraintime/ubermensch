package com.pollub.ubermensch.driver;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DriverRegister {
    @NotBlank
    private String name;

    @NotBlank
    private String licenseNo;

    @NotBlank
    private Vehicle vehicle;
}
