package com.pollub.ubermensch.service;

import com.pollub.ubermensch.dto.Login;
import com.pollub.ubermensch.dto.Register;
import com.pollub.ubermensch.model.Account;
import com.pollub.ubermensch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Register register) {
        accountRepository.save(Account.builder().name(register.getName()).surname(register.getSurname()).email(register.getEmail()).country(register.getCountry()).phoneNumber(register.getPhoneNumber()).role(Account.Role.USER).password(passwordEncoder.encode(register.getPassword())).build());
    }

    public Optional<String> login(Login login) {
        return accountRepository.findByEmail(login.getEmail())
                .filter(account -> passwordEncoder.matches(login.getPassword(), account.getPassword()))
                .map(account -> jwtService.generateToken(account.getId(), login.getEmail(), account.getRole().name()));
    }
}
