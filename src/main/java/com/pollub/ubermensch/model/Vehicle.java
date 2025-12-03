package com.pollub.ubermensch.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class Vehicle {
    private String model;
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private Byte capacity;
    enum VehicleType {
        STANDARD,
        PREMIUM,
        XL
    }
}
