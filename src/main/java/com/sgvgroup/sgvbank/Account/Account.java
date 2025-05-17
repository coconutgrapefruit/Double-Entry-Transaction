package com.sgvgroup.sgvbank.Account;

import com.sgvgroup.sgvbank.LedgerEntries.LedgerEntry;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id", updatable = false, nullable = false)
        private UUID id;

        @Column(name = "account_number", nullable = false, unique = true)
        private String accountNumber;

        @Column(nullable = false)
        private BigDecimal balance;

        @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<LedgerEntry> entries = new ArrayList<>();

        public Account() { }

        public Account(String accountNumber) {
                setAccountNumber(accountNumber);
                this.balance = BigDecimal.valueOf(0.00);
                this.entries = new ArrayList<>();
        }


        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public String getAccountNumber() {
                return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
        }

        public BigDecimal getBalance() {
                return balance;
        }

        public void setBalance(BigDecimal balance) {
                this.balance = balance;
        }

        public List<LedgerEntry> getEntries() {
                return entries;
        }

        public void setEntries(List<LedgerEntry> entries) {
                this.entries = entries;
        }
}