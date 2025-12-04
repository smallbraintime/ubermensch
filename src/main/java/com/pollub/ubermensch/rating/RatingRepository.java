package com.pollub.ubermensch.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDriverIdOrderByCreatedAtDesc(Long driverId);

    void deleteByRiderIdAndDriverId(Long riderId, Long driverId);
}
