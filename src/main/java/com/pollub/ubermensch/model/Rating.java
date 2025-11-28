package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rideId;
    private Long driverId;
    private Long riderId;
    private Byte score;
    private String comment;
    private Date createdAt;
}
