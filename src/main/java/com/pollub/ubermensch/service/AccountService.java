package com.pollub.ubermensch.service;

import com.pollub.ubermensch.dto.DriverRegister;
import com.pollub.ubermensch.model.Account;
import com.pollub.ubermensch.model.Driver;
import com.pollub.ubermensch.repository.AccountRepository;
import com.pollub.ubermensch.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    AccountRepository accountRepository;

    public Driver registerDriver(Long accountId, DriverRegister driverRegister) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        var driver = Driver.builder()
                .avgRating(0.0f)
                .ratingCount(0)
                .name(account.getName())
                .licenseNo(driverRegister.getLicenseNo())
                .vehicle(driverRegister.getVehicle())
                .account(account)
                .build();
        driverRepository.save(driver);
        return driver;
    }

    public void deleteAccount() {}

    public void changePassword() {}

    public void changeEmail() {}
}
