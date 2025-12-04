package com.pollub.ubermensch.service;

import com.pollub.ubermensch.dto.RatingRequest;
import com.pollub.ubermensch.model.Rating;
import com.pollub.ubermensch.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    public void rateDriver(Long driverId, RatingRequest request) {
        // TODO
    }

}
