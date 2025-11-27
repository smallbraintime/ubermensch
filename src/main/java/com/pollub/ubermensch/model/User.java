package com.pollub.ubermensch.model;

import com.pollub.ubermensch.util.Location;
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
    @Enumerated(EnumType.STRING)
    private Role role;
    private String passwordHash;
    @Transient
    private Location location;
}
