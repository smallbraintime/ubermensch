package com.pollub.ubermensch.auth;

import com.pollub.ubermensch.shared.Account;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid Register register) {
        authService.register(register);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid Login login) {
        return ResponseEntity.ok(authService.login(login));
    }

    @GetMapping("/account")
    public ResponseEntity<Account> getAccount() {
        return ResponseEntity.ok(authService.getAccount(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName())));
    }
}
