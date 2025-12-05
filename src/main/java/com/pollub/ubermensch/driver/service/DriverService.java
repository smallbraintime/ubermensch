package com.pollub.ubermensch.driver.service;

import com.pollub.ubermensch.driver.domain.Driver;
import com.pollub.ubermensch.driver.domain.DriverRegister;
import com.pollub.ubermensch.driver.repository.DriverRepository;
import com.pollub.ubermensch.shared.domain.Account;
import com.pollub.ubermensch.shared.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Long registerDriver(Long accountId, DriverRegister driverRegister) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));

        Optional<Driver> existingDriver = driverRepository.findByAccountId(accountId);

        if (!existingDriver.isPresent()) {
            var driver = Driver.builder()
                    .avgRating(0.0f)
                    .licenseNo(driverRegister.getLicenseNo())
                    .vehicle(driverRegister.getVehicle())
                    .account(account)
                    .build();
            return driverRepository.save(driver).getId();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rider already registered for this account");
    }

    @Transactional
    public Driver getDriver(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "driver not found"));
    }

    @Transactional
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));
    }
}
