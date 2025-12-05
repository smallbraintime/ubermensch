package com.pollub.ubermensch.ride;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.Date;

@Data
@AllArgsConstructor
public class DriverAvailablility {
    @NotBlank
    private Long driverId;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String WBUrl; // url for web socket that publishes geo info

    @NotBlank
    private Date createdAt;

}
