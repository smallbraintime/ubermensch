package com.pollub.ubermensch.driver;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private DriverController driverController;

    @PostMapping("/register")
    public ResponseEntity<Void> registerDriver(@RequestBody @Valid DriverRegister register) {
        driverController.registerDriver(register);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{driverId}")
    public ResponseEntity<Driver> getDriver(@RequestParam Long driverId) {
        return driverController.getDriver(driverId);
    }
}
