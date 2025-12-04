//package com.pollub.ubermensch.ride;
//
//import com.pollub.ubermensch.driver.Driver;
//import com.pollub.ubermensch.driver.DriverRepository;
//import com.pollub.ubermensch.shared.Account;
//import com.pollub.ubermensch.shared.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.geo.*;
//import org.springframework.data.redis.connection.RedisGeoCommands;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import tools.jackson.databind.ObjectMapper;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class RideService {
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    RideRepository rideRepository;
//    @Autowired
//    DriverRepository driverRepository;
//    @Autowired
//    AccountRepository accountRepository;
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    public void registerDriverAvailability(Long driverId, Double latitude, Double longitude) {
//        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException(("Driver not found")));
//        redisTemplate.opsForGeo().add("drivers_geo", new Point(longitude, latitude), driver.getId().toString());
//    }
//
//    public void updateDriverLocation(Long driverId, Double latitude, Double longitude) {
//        redisTemplate.opsForGeo().add("drivers_geo", new Point(longitude, latitude), driverId.toString());
//    }
//
//    public List<RideRequest> getRiderRequests(Double latitude, Double longitude, Long radiusKm) {
//        return redisTemplate.opsForGeo()
//                .radius("riders_geo", new Circle(new Point(longitude, latitude), new Distance(radiusKm, Metrics.KILOMETERS)))
//                .getContent()
//                .stream()
//                .map(geoResult -> "ride_request:" + geoResult.getContent().getName())
//                .map(redisTemplate.opsForValue()::get)
//                .filter(Objects::nonNull)
//                .map(json -> {
//                    try {
//                        return mapper.readValue(json, RideRequest.class);
//                    } catch (Exception e) {
//                        return null;
//                    }
//                })
//                .filter(Objects::nonNull)
//                .toList();
//    }
//
//    public Ride acceptRide(Long driverId, Long accountId) {
//        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
//        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
//
//        List<Point> points = redisTemplate.opsForGeo().position("riders_geo", accountId.toString());
//        if (points == null || points.isEmpty() || points.get(0) == null) {
//            throw new RuntimeException("Location not found");
//        }
//        Point dropOffLocation = points.get(0);
//
//        var ride = Ride.builder()
//                .country(account.getCountry())
//                .rider(account)
//                .payment(Payment.builder().status(Payment.PaymentStatus.UNPAID).build())
//                .status(Ride.RideStatus.UNCOMPLETED)
//                .rider(account)
//                .dropoffLocation(dropOffLocation)
//                .build();
//
//        String json = mapper.writeValueAsString(ride);
//        redisTemplate.opsForValue().set("rides:" + ride.getId(), json);
//
//        return ride;
//    }
//
//
//    public void finishRide(Long driverId, Long rideId) {
//
//    }
//
//    public List<DriverAvailablility> getNearbyDrivers(Double latitude, Double longitude, Long radiusKm) {
//        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResults =
//                redisTemplate.opsForGeo()
//                        .radius("drivers_geo",
//                                new Circle(new Point(longitude, latitude), new Distance(radiusKm, Metrics.KILOMETERS)))
//                        .getContent();
//
//        List<Long> ids = geoResults.stream()
//                .map(result -> Long.valueOf(result.getContent().getName()))
//                .toList();
//
//        Map<Long, Point> idToLocation = geoResults.stream()
//                .collect(Collectors.toMap(
//                        r -> Long.valueOf(r.getContent().getName()),
//                        r -> r.getContent().getPoint()
//                ));
//
//        List<Driver> drivers = driverRepository.findAllById(ids);
//        return drivers.stream()
//                .map(driver -> {
//                    Point point = idToLocation.get(driver.getId());
//                    DriverAvailablility dto = new DriverAvailablility();
//                    dto.setDriver(driver);
//                    dto.setLocation(point);
//                    return dto;
//                })
//                .toList();
//    }
//
//    public void requestRide(RideRequest request, Long latitude, Long longitude) {
//        String json = mapper.writeValueAsString(request);
//        redisTemplate.opsForValue().set("ride_request:" + request.getAccountId(), json);
//
//        redisTemplate.opsForGeo().add("riders_geo", new Point(longitude, latitude), request.getAccountId().toString());
//    }
//
//    public void updateRiderLocation(Long accountId, Long latitude, Long longitude) {
//        redisTemplate.opsForGeo().add("riders_geo", new Point(longitude, latitude), accountId.toString());
//    }
//
//    public void cancelRide(Long rideId) {
//    }
//
//    public Optional<Point> getDriverLocation(Long driverId) {
//    }
//
//    public Optional<Ride> getAcceptedRide(Long accountId) {
//    }
//}
