package com.pollub.ubermensch.driver.domain;

import com.pollub.ubermensch.shared.domain.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String licenseNo;

    @Embedded
    private Vehicle vehicle;

    private Float avgRating;
}
