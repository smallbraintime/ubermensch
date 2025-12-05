package com.pollub.ubermensch.ride;

import com.pollub.ubermensch.driver.Driver;
import com.pollub.ubermensch.driver.DriverRepository;
import com.pollub.ubermensch.shared.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RideService {

    private static final String DRIVERS_GEO_KEY = "drivers_geo";
    private static final String RIDERS_GEO_KEY = "riders_geo";
    private static final String DRIVER_AVAILABILITY_PREFIX = "driver_availability:";
    private static final String RIDE_REQUESTS_KEY = "ride_requests";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    RideRepository rideRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    AccountRepository accountRepository;

    private final ObjectMapper mapper = new ObjectMapper();
    private static final Double RADIUSKM = 5.0;

    @Transactional
    public Ride getRide(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride not found."));
    }

    @Transactional
    public void registerDriverAvailability(DriverAvailabilityRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found."));

        String availabilityKey = DRIVER_AVAILABILITY_PREFIX + driver.getId();

        if (redisTemplate.hasKey(availabilityKey)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Driver is already available or currently on a ride.");
        }

        redisTemplate.opsForGeo().add(DRIVERS_GEO_KEY, request.getLocation(), driver.getId().toString());

        var driverAvailability = new DriverAvailablility(
                request.getDriverId(),
                driver.getAccount().getName(),
                driver.getAccount().getPhoneNumber(),
                request.getWBUrl(),
                new Date());

        try {
            String json = mapper.writeValueAsString(driverAvailability);
            redisTemplate.opsForValue().set(availabilityKey, json);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to serialize driver availability.", e);
        }
    }

    @Transactional
    public List<RideRequest> getRideRequests(Point driverLocation) {
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResults = redisTemplate.opsForGeo()
                .radius(RIDERS_GEO_KEY,
                        new Circle(driverLocation, new Distance(RADIUSKM, Metrics.KILOMETERS)))
                .getContent();

        List<String> nearbyRiderIds = geoResults.stream()
                .map(geoResult -> geoResult.getContent().getName())
                .collect(Collectors.toList());

        List<String> jsonRequests = redisTemplate.opsForList().range(RIDE_REQUESTS_KEY, 0, -1);

        if (jsonRequests == null || jsonRequests.isEmpty()) {
            return Collections.emptyList();
        }

        return jsonRequests.stream()
                .map(json -> {
                    try {
                        return mapper.readValue(json, RideRequest.class);
                    } catch (Exception e) {
                        System.err.println("Skipping malformed RideRequest JSON: " + json);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(request -> nearbyRiderIds.contains(String.valueOf(request.getAccountId())))
                .collect(Collectors.toList());
    }

    public List<DriverAvailablility> getNearbyDrivers(Point location) {
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResults = redisTemplate.opsForGeo()
                .radius(DRIVERS_GEO_KEY,
                        new Circle(location, new Distance(RADIUSKM, Metrics.KILOMETERS)))
                .getContent();

        if (geoResults.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> availabilityKeys = geoResults.stream()
                .map(result -> DRIVER_AVAILABILITY_PREFIX + result.getContent().getName())
                .collect(Collectors.toList());

        List<String> jsonResults = redisTemplate.opsForValue().multiGet(availabilityKeys);

        if (jsonResults == null) {
            return Collections.emptyList();
        }

        return jsonResults.stream()
                .filter(java.util.Objects::nonNull)
                .map(json -> {
                    try {
                        return mapper.readValue(json, DriverAvailablility.class);
                    } catch (Exception e) {
                        System.err.println("Skipping malformed DriverAvailablility JSON: " + json);
                        return null;
                    }
                })
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RideRequest> getAllRideRequests() {
        List<String> jsonRequests = redisTemplate.opsForList().range(RIDE_REQUESTS_KEY, 0, -1);

        if (jsonRequests == null || jsonRequests.isEmpty()) {
            return Collections.emptyList();
        }

        return jsonRequests.stream()
                .map(json -> {
                    try {
                        return mapper.readValue(json, RideRequest.class);
                    } catch (Exception e) {
                        System.err.println("Skipping malformed RideRequest JSON: " + json);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public void requestRide(RideRequest request) {
        try {
            request.setCreatedAt(new Date());
            String json = mapper.writeValueAsString(request);
            redisTemplate.opsForList().rightPush(RIDE_REQUESTS_KEY, json);
            RideRequest.Point point =  request.getCurrentLocation();
            redisTemplate.opsForGeo().add(RIDERS_GEO_KEY, new Point(point.getX(), point.getY()), request.getAccountId().toString());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to serialize ride request.", e);
        }
    }
}