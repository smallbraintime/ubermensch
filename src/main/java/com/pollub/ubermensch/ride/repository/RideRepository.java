package com.pollub.ubermensch.ride.repository;

import com.pollub.ubermensch.ride.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
