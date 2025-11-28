package com.pollub.ubermensch.controller;

import com.pollub.ubermensch.model.*;
import com.pollub.ubermensch.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/ride/driver/{driverId}")
    public List<Ride> getRidesByDriver(@PathVariable Long driverId) {
        return dataService.getRidesByDriver(driverId);
    }

    @GetMapping("/ride/rider/{riderId}")
    public List<Ride> getRidesByRider(@PathVariable Long riderId) {
        return dataService.getRidesByRider(riderId);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
        return dataService.getDriver(driverId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rider/{riderId}")
    public ResponseEntity<Rider> getRider(@PathVariable Long riderId) {
        return dataService.getRider(riderId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return dataService.getUser(userId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rating/driver/{driverId}")
    public List<Rating> getRatingsByDriver(@PathVariable Long driverId) {
        return dataService.getRatingsByDriver(driverId);
    }

    @GetMapping("/rating/rider/{riderId}")
    public List<Rating> getRatingsByRider(@PathVariable Long riderId) {
        return dataService.getRatingsByRider(riderId);
    }

    @GetMapping("/rating/ride/{rideId}")
    public ResponseEntity<Rating> getRatingByRide(@PathVariable Long rideId) {
        return dataService.getRatingByRide(rideId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
