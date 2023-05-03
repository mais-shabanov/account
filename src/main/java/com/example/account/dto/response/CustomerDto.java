package com.example.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String id;
    private String name;
    private String surname;
    private Set<CustomerAccountDto> accounts;
}
