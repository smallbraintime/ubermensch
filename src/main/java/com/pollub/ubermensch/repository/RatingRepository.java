package com.pollub.ubermensch.repository;

import com.pollub.ubermensch.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDriverIdOrderByCreatedAtDesc(Long driverId);
    List<Rating> findByRiderIdOrderByCreatedAtDesc(Long riderId);
    Optional<Rating> findByRideIdOrderByCreatedAtDesc(Long rideId);
}
