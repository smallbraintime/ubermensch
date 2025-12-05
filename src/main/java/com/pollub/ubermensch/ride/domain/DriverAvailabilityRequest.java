package com.pollub.ubermensch.ride.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.geo.Point;

@Data
public class DriverAvailabilityRequest {
    @NotNull
    private Long driverId;

    @NotBlank
    private String WBUrl;

    @NotNull
    private Point location;
}