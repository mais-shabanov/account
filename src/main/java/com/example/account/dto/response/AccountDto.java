package com.example.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String id;
    private BigDecimal balance;
    private LocalDateTime creationTime;
    private AccountCustomerDto accountCustomerDto;
    private Set<TransactionDto> transactions;
}
