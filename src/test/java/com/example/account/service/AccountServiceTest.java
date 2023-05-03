package com.example.account.service;


import com.example.account.TestSupport;
import com.example.account.dto.convertor.AccountDtoConverter;
import com.example.account.dto.response.TransactionDto;
import com.example.account.exception.CustomerNotFoundException;
import com.example.account.dto.request.CreateAccountRequest;
import com.example.account.dto.response.AccountCustomerDto;
import com.example.account.dto.response.AccountDto;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountServiceTest extends TestSupport {

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;
    private AccountService accountService;

    private final Customer customer = generateCustomer();
    private final AccountCustomerDto accountCustomerDto = new AccountCustomerDto(
            "928d7d2c-50d3-485e-b48f-6ab076f7dbdd",
            "john",
            "snow");

    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        customerService = mock(CustomerService.class);
        accountDtoConverter = mock(AccountDtoConverter.class);
        Clock clock = mock(Clock.class);
        accountService = new AccountService(accountRepository, customerService, accountDtoConverter, clock);
        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        CreateAccountRequest createAccountRequest = generateCreateAccountRequest(0);
        when(customerService.findCustomerById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd")).thenThrow(new CustomerNotFoundException("test-exception"));
        assertThrows(CustomerNotFoundException.class, () -> accountService.createAccount(createAccountRequest));
        verify(customerService).findCustomerById(createAccountRequest.getCustomerId());
        verifyNoInteractions(accountRepository);
        verifyNoInteractions(accountDtoConverter);
    }

    private Account generateAccount(int balance) {
        return new Account("218fbed0-b845-4271-a598-40a7c17d353e", new BigDecimal(balance), getLocalDateTime(), customer, new HashSet<>()  );
    }
}
