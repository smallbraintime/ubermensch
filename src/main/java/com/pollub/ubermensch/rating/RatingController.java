package com.pollub.ubermensch.rating;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/rate")
    public ResponseEntity<Void> rateDriver(@RequestBody @Valid RatingRequest request) {
        ratingService.rateDriver(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ratings/{driverId}")
    public ResponseEntity<List<Rating>> getRatingsOyDriver(@RequestParam Long driverId) {
        return ResponseEntity.ok(ratingService.getRatingsOfDriver(driverId));
    }
}
