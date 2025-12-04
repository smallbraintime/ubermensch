package com.pollub.ubermensch.controller;

import com.pollub.ubermensch.dto.RatingRequest;
import com.pollub.ubermensch.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/rate")
    public void rateDriver(@RequestBody RatingRequest request) {
        Long accountId = Long.valueOf(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        ratingService.rateDriver(accountId, request);
    }
}
