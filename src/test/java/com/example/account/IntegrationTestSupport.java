package com.example.account;

import com.example.account.dto.convertor.CustomerDtoConverter;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class IntegrationTestSupport extends TestSupport {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public AccountService accountService;

    @Autowired
    public CustomerDtoConverter customerDtoConverter;

    public final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
    }
}
