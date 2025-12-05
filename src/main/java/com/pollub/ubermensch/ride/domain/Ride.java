package com.pollub.ubermensch.ride.domain;

import com.pollub.ubermensch.driver.domain.Driver;
import com.pollub.ubermensch.shared.domain.Account;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.Date;

@Entity
@Data
@Builder
public class Ride {
    public enum RideStatus {
        COMPLETED,
        PENDING,
        ACCEPTED,
        CANCELLED,
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

    private Date acceptedAt;

    private Date startedAt;

    @Embedded
    private Payment payment;
}
