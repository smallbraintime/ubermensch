package com.pollub.ubermensch.ride;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.geo.Point;
import java.util.Date;

@Data
public class DriverAvailabilityRequest {
    @NotBlank
    private Long riderId;

    @NotBlank
    private Point location;

    @NotBlank
    private Date createdAt;
}
