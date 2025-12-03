package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Payment {
    enum PaymentMethod {
        CARD,
        CASH,
    }

    enum PaymentStatus {
        UNPAID,
        PAID
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id")
    private Ride ride;
    @Embedded
    private Money fare;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
