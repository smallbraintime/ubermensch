package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Ride {
    enum RideStatus {
        PENDING,
        COMPLETED,
        UNCOMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account rider;
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    private String pickupCity;
    private String pickupStreet;
    private Long pickupLatitude;
    private Long pickupLongitude;
    private String dropoffCity;
    private String dropoffStreet;
    private Long dropoffLatitude;
    private Long dropoffLongitude;
    private String country;
    @Enumerated(EnumType.STRING)
    private RideStatus status;
    private Date requestedAt;
    private Date startedAt;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
