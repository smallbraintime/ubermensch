package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Vehicle {
    enum VehicleType {
        STANDARD,
        PREMIUM,
        XL
    }

    private String model;
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private Byte capacity;
}
