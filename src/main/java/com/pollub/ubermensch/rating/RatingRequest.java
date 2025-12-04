package com.pollub.ubermensch.rating;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RatingRequest {
    @NotBlank
    private Long driverId;

    @NotBlank
    private Byte score;

    @NotBlank
    private String comment;
}
