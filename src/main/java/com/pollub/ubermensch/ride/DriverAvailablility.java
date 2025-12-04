package com.pollub.ubermensch.ride;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.geo.Point;

@Data
public class DriverAvailablility {
    @NotBlank
    Long driverId;

    @NotBlank
    String name;

    @NotBlank
    String phoneNumber;

    @NotBlank
    private Point location;
}
