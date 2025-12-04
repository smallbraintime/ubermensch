package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account rider;
    private Byte score;
    private String comment;
    private Date createdAt;
}
