package com.pollub.ubermensch.rating.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingRequest {
    @NotNull
    private Long driverId;

    @NotNull
    @Min(value = 1, message = "Score must be at least 1.")
    @Max(value = 5, message = "Score cannot exceed 5.")
    private Byte score;

    @NotBlank
    private String comment;
}