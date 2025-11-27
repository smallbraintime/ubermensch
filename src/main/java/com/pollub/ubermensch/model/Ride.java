package com.pollub.ubermensch.model;

import com.pollub.ubermensch.util.Location;
import com.pollub.ubermensch.util.Money;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
public class Ride {
    enum RideStatus {
        REQUESTED,
        ACCEPTED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    enum PaymentStatus {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long riderId;
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private Location pickupLocation;
    private Location dropoffLocation;
    private String country;
    private RideStatus status;
    private Date requestedAt;
    private Date startedAt;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
