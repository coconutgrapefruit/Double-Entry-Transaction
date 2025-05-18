package com.sgvgroup.sgvbank.Transaction;

import com.sgvgroup.sgvbank.Account.Account;
import com.sgvgroup.sgvbank.Account.AccountRepository;
import com.sgvgroup.sgvbank.LedgerEntries.LedgerEntry;
import com.sgvgroup.sgvbank.LedgerEntries.LedgerEntryRepository;
import com.sgvgroup.sgvbank.Transaction.dto.TransactionDto;
import com.sgvgroup.sgvbank.Transaction.dto.TransactionRequestDto;
import com.sgvgroup.sgvbank.enums.EntryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            LedgerEntryRepository ledgerEntryRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    @Transactional
    public TransactionDto makeTransaction(TransactionRequestDto request) {

        Account from = accountRepository.findById(request.fromAccount())
                .orElseThrow(() -> new IllegalArgumentException("account not found"));
        Account to = accountRepository.findById(request.toAccount())
                .orElseThrow(() -> new IllegalArgumentException("account not found"));

        if (from.getBalance().compareTo(request.amount()) < 0) {
            throw new IllegalArgumentException("not enough balance");
        }

        from.setBalance(from.getBalance().subtract(request.amount()));
        to.setBalance(to.getBalance().add(request.amount()));
        accountRepository.save(from);
        accountRepository.save(to);

        LedgerEntry debit = ledgerEntryRepository.save(
                new LedgerEntry(from, request.amount().negate(), EntryType.DEBIT));
        LedgerEntry credit = ledgerEntryRepository.save(
                new LedgerEntry(to, request.amount(), EntryType.CREDIT));
        Transaction transaction = transactionRepository.save(
                new Transaction(from, to, request.amount()));

        return TransactionDto.fromEntity(transaction);
    }

    public TransactionDto getTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("transaction not found"));
        return TransactionDto.fromEntity(transaction);
    }

    public Page<TransactionDto> getTransactionByAccount(UUID id, Pageable pageable) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("account not found"));

        return transactionRepository.findByAccountId(id, pageable)
                .map(TransactionDto::fromEntity);
    }
}
