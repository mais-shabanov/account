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
public class CustomerAccountDto {

    private String id;
    private BigDecimal balance = BigDecimal.ZERO;
    private Set<TransactionDto> transactions;
    private LocalDateTime creationDate;
}
