package com.pollub.ubermensch.ride;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class RideRequest {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Point {
        Double x;
        Double y;
    }

    @NotNull
    private Long accountId;

    @NotNull
    @Valid
    private Point currentLocation;

    @NotNull
    @Valid
    private Point dropoffLocation;

    @NotNull
    private Date createdAt;
}
