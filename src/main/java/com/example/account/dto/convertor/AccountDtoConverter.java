package com.example.account.dto.convertor;

import com.example.account.dto.response.AccountDto;
import com.example.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final AccountCustomerDtoConverter accountCustomerDtoConvertor;
    private final TransactionDtoConverter transactionDtoConvertor;

    public AccountDtoConverter(
            AccountCustomerDtoConverter accountCustomerDtoConvertor,
            TransactionDtoConverter transactionDtoConvertor
    ) {
        this.accountCustomerDtoConvertor = accountCustomerDtoConvertor;
        this.transactionDtoConvertor = transactionDtoConvertor;
    }

    public AccountDto convertToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getBalance(),
                account.getCreationTime(),
                accountCustomerDtoConvertor.convertToAccountCustomerDto(account.getCustomer()),
                account.getTransactions().stream()
                        .map(t -> transactionDtoConvertor.convertToTransactionDto(t))
                        .collect(Collectors.toSet())
        );
    }
}
