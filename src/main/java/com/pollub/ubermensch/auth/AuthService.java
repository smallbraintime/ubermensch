package com.pollub.ubermensch.auth;

import com.pollub.ubermensch.shared.Account;
import com.pollub.ubermensch.shared.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Register register) {
        accountRepository.save(Account.builder()
                .name(register.getName())
                .email(register.getEmail())
                .phoneNumber(register.getPhoneNumber())
                .role(Account.Role.USER)
                .password(passwordEncoder.encode(register.getPassword()))
                .build());
    }


    @Transactional
    public String login(Login login) {
        return accountRepository.findByEmail(login.getEmail())
                .filter(account -> passwordEncoder.matches(login.getPassword(), account.getPassword()))
                .map(account -> jwtService.generateToken(
                        account.getId(),
                        login.getEmail(),
                        account.getRole().name()
                ))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid email or password"
                ));
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
