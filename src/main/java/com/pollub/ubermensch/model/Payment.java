package com.pollub.ubermensch.model;

import com.pollub.ubermensch.util.Money;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Payment {
    enum PaymentMethod {
        CARD,
        CASH,
    }

    enum PaymentStatus {
        PENDING,
        PAID,
        FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rideId;
    private Money fare;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
