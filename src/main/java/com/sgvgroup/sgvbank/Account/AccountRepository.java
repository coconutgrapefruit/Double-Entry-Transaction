package com.sgvgroup.sgvbank.Account;

import jakarta.annotation.Nonnull;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Override
    @Lock(value = LockModeType.OPTIMISTIC)
    Optional<Account> findById(@Nonnull UUID id);
}
