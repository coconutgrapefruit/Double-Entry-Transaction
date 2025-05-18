package com.sgvgroup.sgvbank.LedgerEntries;

import com.sgvgroup.sgvbank.enums.EntryType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record LedgerEntryDto(
        UUID id,
        UUID transactionId,
        UUID accountId,
        BigDecimal amount,
        EntryType entryType,
        Instant createdAt
) {
    public static LedgerEntryDto fromEntity(LedgerEntry ledgerEntry) {
        return new LedgerEntryDto(
                ledgerEntry.getId(),
                ledgerEntry.getTransaction().getId(),
                ledgerEntry.getAccount().getId(),
                ledgerEntry.getAmount(),
                ledgerEntry.getEntryType(),
                ledgerEntry.getCreatedAt()
        );
    }
}
