package com.sgvgroup.sgvbank.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.fromAccount.id = :accountId OR t.toAccount.id = :accountId " +
            "ORDER BY t.createdAt DESC")
    Page<Transaction> findByAccountId(UUID accountId, Pageable pageable);
}
