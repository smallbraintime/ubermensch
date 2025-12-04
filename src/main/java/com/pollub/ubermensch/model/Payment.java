package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Payment {
    public enum PaymentMethod {
        CARD,
        CASH,
    }

    public enum PaymentStatus {
        UNPAID,
        PAID
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
    @Embedded
    private Money fare;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
