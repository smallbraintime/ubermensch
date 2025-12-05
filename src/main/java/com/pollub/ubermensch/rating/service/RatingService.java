package com.pollub.ubermensch.rating.service;

import com.pollub.ubermensch.rating.domain.Rating;
import com.pollub.ubermensch.rating.domain.RatingRequest;
import com.pollub.ubermensch.rating.repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Transactional
    public void rateDriver(Long riderId, RatingRequest request) {
        ratingRepository.deleteByRiderIdAndDriverId(riderId, request.getDriverId());
        ratingRepository.save(Rating.builder()
                .driverId(request.getDriverId())
                .riderId(riderId)
                .score(request.getScore())
                .comment(request.getComment())
                .createdAt(new Date()).build());
    }

    @Transactional
    public List<Rating> getRatingsOfDriver(Long driverId) {
        return ratingRepository.findByDriverIdOrderByCreatedAtDesc(driverId);
    }
}
