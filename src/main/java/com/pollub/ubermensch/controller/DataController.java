package com.pollub.ubermensch.controller;

import com.pollub.ubermensch.model.Account;
import com.pollub.ubermensch.model.Driver;
import com.pollub.ubermensch.model.Rating;
import com.pollub.ubermensch.model.Ride;
import com.pollub.ubermensch.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("public/rides/driver")
    public List<Ride> getRidesByDriver() {
        return dataService.getRidesByDriver(getId());
    }

    @GetMapping("public/rides/rider")
    public List<Ride> getRidesByRider() {
        return dataService.getRidesByRider(getId());
    }

    @GetMapping("public/account")
    public ResponseEntity<Account> getAccount() {
        return dataService.getAccount(getId()).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("public/driver")
    public ResponseEntity<Driver> getDriver() {
        return dataService.getDriver(getId()).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("public/ratings/driver/{driverId}")
    public List<Rating> getRatingsByDriver(@PathVariable Long driverId) {
        return dataService.getRatingsByDriver(driverId);
    }

    @GetMapping("public/ratings/rider/{riderId}")
    public List<Rating> getRatingsByRider(@PathVariable Long riderId) {
        return dataService.getRatingsByRider(riderId);
    }

    @GetMapping("public/rating/ride/{rideId}")
    public ResponseEntity<Rating> getRatingByRide(@PathVariable Long rideId) {
        return dataService.getRatingByRide(rideId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("private/rides/driver/{driverId}")
    public List<Ride> getRidesByDriver(@PathVariable Long driverId) {
        return dataService.getRidesByDriver(driverId);
    }

    @GetMapping("private/rides/rider/{riderId}")
    public List<Ride> getRidesByRider(@PathVariable Long riderId) {
        return dataService.getRidesByRider(riderId);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
        return dataService.getDriver(driverId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("private/account/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        return dataService.getAccount(accountId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("private/ride/{rideId}")
    public ResponseEntity<Ride> getRides(@PathVariable Long rideId) {
        return dataService.getRide(rideId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("private/rides")
    public List<Ride> getRides() {
        return dataService.getRides();
    }

    @GetMapping("private/accounts")
    public List<Account> getAccounts() {
        return dataService.getAccounts();
    }

    @GetMapping("private/drivers")
    public List<Driver> getDrivers() {
        return dataService.getDrivers();
    }

    private Long getId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
