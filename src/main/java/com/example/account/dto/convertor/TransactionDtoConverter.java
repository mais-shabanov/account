package com.example.account.dto.convertor;

import com.example.account.dto.response.TransactionDto;
import com.example.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convertToTransactionDto(Transaction transaction) {
        return new TransactionDto(
          transaction.getId(),
          transaction.getAmount(),
          transaction.getTransactionType(),
          transaction.getTransactionDate()
        );
    }
}
