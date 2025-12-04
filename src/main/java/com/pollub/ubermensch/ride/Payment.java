package com.pollub.ubermensch.ride;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Embedded
public class Payment {
    public enum PaymentMethod {
        CARD,
        CASH,
    }

    public enum PaymentStatus {
        UNPAID,
        PAID
    }

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
