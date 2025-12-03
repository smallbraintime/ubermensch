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
    private AccountRepository accountRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public List<Ride> getRidesByDriver(String email) {
        return rideRepository.findByDriverAccountEmailOrderByStartedAtDesc(email);
    }

    public List<Ride> getRidesByRider(String email) {
        return rideRepository.findByRiderEmailOrderByStartedAtDesc(email);
    }

    public Optional<Account> getAccount(String email) {
        return accountRepository.findByEmail(email);
    }

    public Optional<Driver> getDriver(String email) {
        return driverRepository.findByAccountEmail(email);
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


    public List<Ride> getRidesByDriver(Long driverId) {
        return rideRepository.findByDriverIdOrderByStartedAtDesc(driverId);
    }

    public List<Ride> getRidesByRider(Long riderId) {
        return rideRepository.findByRiderIdOrderByStartedAtDesc(riderId);
    }

    public Optional<Driver> getDriver(Long driverId) {
        return driverRepository.findById(driverId);
    }

    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Optional<Ride> getRide(Long rideId) {
        return rideRepository.findById(rideId);
    }

    public List<Ride> getRides() {
        return rideRepository.findAll();
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }
}
