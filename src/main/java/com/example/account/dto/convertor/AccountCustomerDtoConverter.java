package com.example.account.dto.convertor;

import com.example.account.dto.response.AccountCustomerDto;
import com.example.account.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class AccountCustomerDtoConverter {
    public AccountCustomerDto convertToAccountCustomerDto(Customer customer) {
        return new AccountCustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getSurname()
        );
    }
}
