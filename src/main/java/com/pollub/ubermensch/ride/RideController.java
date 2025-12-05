package com.pollub.ubermensch.ride;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    @Autowired
    RideService rideService;

    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRide(@PathVariable Long rideId) {
        Ride ride = rideService.getRide(rideId);
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/driver/available")
    public ResponseEntity<Void> registerDriverAvailability(@RequestBody @Valid DriverAvailabilityRequest request) {
        rideService.registerDriverAvailability(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/driver/requests")
    public ResponseEntity<List<RideRequest>> getRideRequests(@RequestBody @Valid Point location) {
        List<RideRequest> requests = rideService.getRideRequests(location);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/driver/allrequests")
    public ResponseEntity<List<RideRequest>> getAllRideRequests() {
        List<RideRequest> requests = rideService.getAllRideRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/rider/drivers")
    public ResponseEntity<List<DriverAvailablility>> getNearbyDrivers(@RequestBody @Valid Point location) {
        List<DriverAvailablility> drivers = rideService.getNearbyDrivers(location);
        return ResponseEntity.ok(drivers);
    }

    @PostMapping("/rider/request")
    public ResponseEntity<Void> requestRide(@RequestBody @Valid RideRequest request) {
        rideService.requestRide(request);
        return ResponseEntity.status(201).build();
    }
}