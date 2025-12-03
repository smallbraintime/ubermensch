package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    private String name;
    private String licenseNo;
    @Embedded
    private Vehicle vehicle;
    private Float avgRating;
    private Integer ratingCount;
}
