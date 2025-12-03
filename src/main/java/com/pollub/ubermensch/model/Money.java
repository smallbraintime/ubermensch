package com.pollub.ubermensch.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.math.BigDecimal;

@Embeddable
@Data
public class Money {
    private BigDecimal amount;
    private String currency;
}
