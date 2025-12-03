package com.pollub.ubermensch.controller;

import com.pollub.ubermensch.dto.Login;
import com.pollub.ubermensch.dto.Register;
import com.pollub.ubermensch.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register register) {
        try {
            authService.register(register);
            return ResponseEntity.status(HttpStatus.CREATED).body("Account registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        return authService.login(login)
                .map(token -> ResponseEntity.ok(token))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password"));
    }
}
