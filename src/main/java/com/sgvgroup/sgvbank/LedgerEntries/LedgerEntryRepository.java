package com.sgvgroup.sgvbank.LedgerEntries;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, UUID> {
    Page<LedgerEntry> findByAccountId(UUID accountId);
    Page<LedgerEntry> findByTransactionId(UUID transactionId);
}
