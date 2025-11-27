package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class Vehicle {
    enum VehicleType {
        STANDARD,
        PREMIUM,
        XL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long driverId;
    private String model;
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private Byte capacity;
}
