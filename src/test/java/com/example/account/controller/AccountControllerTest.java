package com.example.account.controller;

import com.example.account.IntegrationTestSupport;
import com.example.account.dto.request.CreateAccountRequest;
import com.example.account.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.attribute.standard.Media;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext
@SpringBootTest
public class AccountControllerTest extends IntegrationTestSupport {

    @Test
    public void testCreateAccount_whenCustomerIdExists_shouldCreateAccountAndReturnAccountDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        CreateAccountRequest createAccountRequest = generateCreateAccountRequest(customer.getId(), 50);
        mockMvc.perform(
                        post(ACCOUNT_API_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(createAccountRequest))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.balance", is(50)))
                .andExpect(jsonPath("$.accountCustomerDto.id", is(customer.getId())))
                .andExpect(jsonPath("$.accountCustomerDto.name", is(customer.getName())))
                .andExpect(jsonPath("$.accountCustomerDto.surname", is(customer.getSurname())))
                .andExpect(jsonPath("$.transactions", hasSize(1)));

    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        CreateAccountRequest createAccountRequest = generateCreateAccountRequest("928d7d2c-50d3-485e-b48f", 50);
        mockMvc.perform(
                post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(createAccountRequest))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAccount_whenRequestHasNoCustomerId_shouldReturn400BadRequest() throws Exception {
        CreateAccountRequest createAccountRequest = generateCreateAccountRequest("", 50);
        mockMvc.perform(
                post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(createAccountRequest))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateAccount_whenRequestHasLessThanZeroInitialCreditValue_shouldReturn400BadRequest() throws Exception {
        CreateAccountRequest createAccountRequest = generateCreateAccountRequest("928d7d2c-50d3-485e-b48f-6ab076f7dbdd", -50);
        mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(createAccountRequest)))
                .andExpect(status().isBadRequest());
    }
}
