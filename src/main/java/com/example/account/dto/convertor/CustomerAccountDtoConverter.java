package com.example.account.dto.convertor;

import com.example.account.dto.response.CustomerAccountDto;
import com.example.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class CustomerAccountDtoConverter {

    private final TransactionDtoConverter transactionDtoConvertor;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConvertor) {
        this.transactionDtoConvertor = transactionDtoConvertor;
    }

    public CustomerAccountDto convertToCustomerAccountDto(Account account) {
        return new CustomerAccountDto(
                account.getId(),
                account.getBalance(),
                account.getTransactions().stream()
                        .map(t -> transactionDtoConvertor.convertToTransactionDto(t))
                        .collect(Collectors.toSet()),
                account.getCreationTime()
        );
    }
}
