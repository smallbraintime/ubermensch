package com.pollub.ubermensch.model;

import com.pollub.ubermensch.util.Location;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
public class Ride {
    enum RideStatus {
        COMPLETED,
        UNCOMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
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
