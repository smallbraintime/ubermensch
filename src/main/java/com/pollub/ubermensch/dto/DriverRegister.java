package com.pollub.ubermensch.dto;

import com.pollub.ubermensch.model.Vehicle;
import lombok.Data;

@Data
public class DriverRegister {
    private String name;
    private String licenseNo;
    private Vehicle vehicle;
}
