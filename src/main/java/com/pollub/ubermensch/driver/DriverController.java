package com.pollub.ubermensch.driver;

import com.pollub.ubermensch.shared.Account;
import com.pollub.ubermensch.shared.AccountRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
