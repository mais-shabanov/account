package com.example.account.service;

import com.example.account.dto.convertor.AccountDtoConverter;
import com.example.account.dto.request.CreateAccountRequest;
import com.example.account.dto.response.AccountDto;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConvertor;
    private final Clock clock;

    public AccountService(
            AccountRepository accountRepository,
            CustomerService customerService,
            AccountDtoConverter accountDtoConvertor,
            Clock clock
    ) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConvertor = accountDtoConvertor;
        this.clock = clock;
    }

    @Transactional
    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
//                LocalDateTime.now()
                getLocalDateTimeNow()
        );
        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    LocalDateTime.now(),
                    account
            );
            account.getTransactions().add(transaction);
        }
        return accountDtoConvertor.convertToAccountDto(accountRepository.save(account));
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone()
        );
    }

}
