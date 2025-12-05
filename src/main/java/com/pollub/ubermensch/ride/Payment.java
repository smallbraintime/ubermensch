package com.pollub.ubermensch.ride;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class Payment {
    public enum PaymentMethod {
        CARD,
        CASH,
    }

    public enum PaymentStatus {
        UNPAID,
        PAID
    }

    @Embedded
    private Money fare;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
