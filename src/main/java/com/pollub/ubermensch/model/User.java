package com.pollub.ubermensch.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class User {
    public enum Role {
        RIDER,
        DRIVER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String country;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String passwordHash;
}
