package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.geo.*;

import java.util.Date;

@Entity
@Data
@Builder
public class Ride {
    public enum RideStatus {
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
    private Point pickupLocation;
    private Point dropoffLocation;
    private String country;
    @Enumerated(EnumType.STRING)
    private RideStatus status;
    private Date startedAt;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
