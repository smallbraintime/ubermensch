package com.pollub.ubermensch.ride;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.Date;

@Data
public class RideRequest {
    @NotBlank
    private Long accountId;

    @NotBlank
    private Point currentLocation;

    @NotBlank
    private Point dropoffLocation;

    @NotBlank
    private Date createdAt;
}
