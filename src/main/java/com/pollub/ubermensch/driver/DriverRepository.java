package com.pollub.ubermensch.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByAccountEmail(String email);
    Optional<Driver> findByAccountId(Long accountId);
}
