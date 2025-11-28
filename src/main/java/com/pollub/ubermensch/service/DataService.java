package com.pollub.ubermensch.service;

import com.pollub.ubermensch.model.*;
import com.pollub.ubermensch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public List<Ride> getRidesByDriver(Long driverId) {
        return rideRepository.findByDriverIdOrderByStartedAtDesc(driverId);
    }

    public List<Ride> getRidesByRider(Long riderId) {
        return rideRepository.findByRiderIdOrderByStartedAtDesc(riderId);
    }

    public Optional<Driver> getDriver(Long driverId) {
        return driverRepository.findById(driverId);
    }

    public Optional<Rider> getRider(Long riderId) {
        return riderRepository.findById(riderId);
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public List<Rating> getRatingsByDriver(Long driverId) {
        return ratingRepository.findByDriverIdOrderByCreatedAtDesc(driverId);
    }

    public List<Rating> getRatingsByRider(Long riderId) {
        return ratingRepository.findByRiderIdOrderByCreatedAtDesc(riderId);
    }

    public Optional<Rating> getRatingByRide(Long rideId) {
        return ratingRepository.findByRideIdOrderByCreatedAtDesc(rideId);
    }
}
