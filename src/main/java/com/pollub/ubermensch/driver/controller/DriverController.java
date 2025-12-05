package com.pollub.ubermensch.driver.controller;

import com.pollub.ubermensch.driver.domain.DriverRegister;
import com.pollub.ubermensch.driver.service.DriverService;
import com.pollub.ubermensch.driver.domain.Driver;
import com.pollub.ubermensch.shared.domain.Account;
import com.pollub.ubermensch.shared.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/register")
    public ResponseEntity<Long> registerDriver(@RequestBody @Valid DriverRegister register) {
        Long id = driverService.registerDriver(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()), register);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/get/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getDriver(driverId));
    }

    @GetMapping("/account") // temporarily
    public ResponseEntity<Account> getAccount() {
        return ResponseEntity.ok(driverService.getAccount(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }
}
