package com.sgvgroup.sgvbank.Transaction.dto;

import com.sgvgroup.sgvbank.Transaction.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        UUID fromAccount,
        UUID toAccount,
        BigDecimal amount,
        Instant time
) {
    public static TransactionDto fromEntity(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getFromAccount().getId(),
                transaction.getToAccount().getId(),
                transaction.getAmount(),
                transaction.getCreatedAt()
        );
    }
}
