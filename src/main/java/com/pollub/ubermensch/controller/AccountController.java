package com.pollub.ubermensch.controller;

import com.pollub.ubermensch.dto.DriverRegister;
import com.pollub.ubermensch.model.Driver;
import com.pollub.ubermensch.model.Ride;
import com.pollub.ubermensch.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/public/driverregister")
    public ResponseEntity<Driver> registerDriver(@RequestBody DriverRegister register) {
        Long userId = Long.valueOf(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        Driver driver = accountService.registerDriver(userId, register);
        return ResponseEntity.ok(driver);
    }

}
