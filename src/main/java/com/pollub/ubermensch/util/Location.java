package com.pollub.ubermensch.util;

import lombok.Data;

@Data
public class Location {
    private String city;
    private String street;
    private Long latitude;
    private Long longitude;
}
