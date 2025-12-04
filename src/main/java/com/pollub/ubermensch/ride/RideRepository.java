package com.pollub.ubermensch.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDriverIdOrderByStartedAtDesc(Long driverId);

    List<Ride> findByRiderIdOrderByStartedAtDesc(Long riderId);
}
