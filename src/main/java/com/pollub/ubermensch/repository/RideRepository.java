package com.pollub.ubermensch.repository;

import com.pollub.ubermensch.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDriverIdOrderByStartedAtDesc(Long driverId);
    List<Ride> findByRiderIdOrderByStartedAtDesc(Long riderId);
    List<Ride> findByRiderEmailOrderByStartedAtDesc(String email);
    List<Ride> findByDriverAccountEmailOrderByStartedAtDesc(String email);
}
