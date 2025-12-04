package com.pollub.ubermensch.ride;

import com.pollub.ubermensch.driver.Driver;
import com.pollub.ubermensch.shared.Account;
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

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
