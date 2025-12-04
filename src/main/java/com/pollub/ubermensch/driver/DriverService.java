package com.pollub.ubermensch.driver;

import com.pollub.ubermensch.shared.Account;
import com.pollub.ubermensch.shared.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public void registerDriver(Long accountId, DriverRegister driverRegister) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"));

        var driver = Driver.builder()
                .avgRating(0.0f)
                .licenseNo(driverRegister.getLicenseNo())
                .vehicle(driverRegister.getVehicle())
                .account(account)
                .build();
        driverRepository.save(driver);
    }

    @Transactional
    public Driver getDriver(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "driver not found"));
    }
}
