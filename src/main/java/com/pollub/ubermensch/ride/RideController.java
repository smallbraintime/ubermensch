package com.pollub.ubermensch.ride;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRide(@RequestParam Long rideId) {
    }

    @PostMapping("/driver/available")
    public ResponseEntity<Void> registerDriverAvailability(@RequestBody @Valid DriverAvailabilityRequest request) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/driver/update")
    public ResponseEntity<Void> updateDriverLocation(@RequestBody @Valid Point location) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/driver/location/{accountId}")
    public ResponseEntity<Point> getRiderLocation() {
    }

    @GetMapping("/driver/requests")
    public ResponseEntity<List<RideRequest>> getRiderRequests() {
    }

    @PostMapping("/driver/accept/{accountId}")
    public ResponseEntity<Void> acceptRide(Long accountId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rider/drivers")
    public ResponseEntity<List<DriverAvailablility>> getNearbyDrivers() {
    }

    @PostMapping("/rider/request/{riderId}")
    public ResponseEntity<Void> requestRide(@RequestBody @Valid RideRequest request) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rider/finish/{rideId}")
    public ResponseEntity<Void> finishRide(@RequestParam Long rideId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rider/cancel{rideId}")
    public ResponseEntity<Void> cancelRide(@RequestParam Long rideId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rider/status/{accountId}")
    public ResponseEntity<Ride.RideStatus> getRideStatus(@RequestParam Long accountId) {
    }

    @GetMapping("/rider/location/{driverId}")
    public ResponseEntity<Point> getDriverLocation(@RequestParam Long driverId) {
    }
}
