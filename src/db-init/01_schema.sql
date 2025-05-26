create table if not exists accounts (
    id UUID primary key default gen_random_uuid(),
    account_number varchar(20) not null unique,
    balance numeric(19,4) not null default 0 check (balance >= 0),
    version bigint NOT NULL DEFAULT 0
);

create table if not exists ledger_entries (
    id UUID primary key default gen_random_uuid(),
    account_id UUID not null references accounts(id),
    transaction_id UUID not null references transactions(id),
    amount numeric(19,4) not null,
    entry_type varchar(6) not null check(entry_type in (DEBIT, CREDIT)),
    created_at timestampz not null default now()
    unique (transaction_id, entry_type)
);

create table if not exists transactions (
    id UUID primary key default gen_random_uuid(),
    from_account_id UUID not null references accounts(id),
    to_account_id UUID not null references accounts(id),
    amount numeric(19,4) not null,
    created_at timestampz not null default now()
)

create index idx_tx_from_created on transactions(from_account_id, created_at desc);
create index idx_tx_to_created on transactions(to_account_id, created_at desc);

create table if not exists outbox (
    id bigint primary key auto_increment,
    aggregate_type varchar(20),
    aggregate_id UUID not null,
    payload JSON,
    created_at timestampz not null default now(),
    published boolean default false
    )