package com.sgvgroup.sgvbank.Transaction.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequestDto(
        UUID fromAccount,
        UUID toAccount,
        BigDecimal amount
) {
}
