package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String licenseNo;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private Float avgRating;
    private Integer ratingCount;
    private boolean isAvailable;
}
