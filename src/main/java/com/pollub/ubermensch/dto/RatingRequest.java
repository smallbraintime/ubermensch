package com.pollub.ubermensch.dto;

import lombok.Data;

@Data
public class RatingRequest {
    private Byte score;
    private String comment;
}
