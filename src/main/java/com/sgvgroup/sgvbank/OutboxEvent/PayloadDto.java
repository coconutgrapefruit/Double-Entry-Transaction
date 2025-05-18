package com.sgvgroup.sgvbank.OutboxEvent;

import com.sgvgroup.sgvbank.LedgerEntries.LedgerEntryDto;
import com.sgvgroup.sgvbank.Transaction.dto.TransactionDto;

public record PayloadDto(
        TransactionDto transaction,
        LedgerEntryDto debit,
        LedgerEntryDto credit
) {
}
