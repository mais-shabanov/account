package com.example.account.dto.convertor;

import com.example.account.dto.response.CustomerDto;
import com.example.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    private final CustomerAccountDtoConverter customerAccountDtoConvertor;

    public CustomerDtoConverter(CustomerAccountDtoConverter customerAccountDtoConvertor) {
        this.customerAccountDtoConvertor = customerAccountDtoConvertor;
    }

    public CustomerDto convertToCustomerDto(Customer customer) {
        return new CustomerDto(
          customer.getId(),
          customer.getName(),
          customer.getSurname(),
          customer.getAccounts().stream()
                  .map(a -> customerAccountDtoConvertor.convertToCustomerAccountDto(a))
                  .collect(Collectors.toSet())
        );
    }
}
