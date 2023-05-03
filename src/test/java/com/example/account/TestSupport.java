package com.example.account;

import com.example.account.dto.request.CreateAccountRequest;
import com.example.account.model.Account;
import com.example.account.model.Customer;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TestSupport {

    public Instant getCurrentInstant() {
        String instantExpected = "2021-06-15T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public static final String CUSTOMER_API_ENDPOINT = "/v1/customer";
    public static final String ACCOUNT_API_ENDPOINT = "/v1/account";

    public Customer generateCustomer() {
        return new Customer("928d7d2c-50d3-485e-b48f-6ab076f7dbdd", "john", "snow", Set.of());
    }

    public CreateAccountRequest generateCreateAccountRequest(int initialCredit) {
        return generateCreateAccountRequest("928d7d2c-50d3-485e-b48f-6ab076f7dbdd", initialCredit);
    }

    public CreateAccountRequest generateCreateAccountRequest(String customerId, int initialCredit) {
        return new CreateAccountRequest(customerId, new BigDecimal(initialCredit));
    }

}
