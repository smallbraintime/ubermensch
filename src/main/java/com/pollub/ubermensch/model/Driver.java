package com.pollub.ubermensch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"id", "role", "password", "email"})
    private Account account;
    private String name;
    private String licenseNo;
    @Embedded
    private Vehicle vehicle;
    private Float avgRating;
    private Integer ratingCount;
}
