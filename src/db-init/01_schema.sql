create table if not exists accounts (
    id UUID primary key default gen_random_uuid(),
    account_number varchar(20) not null unique,
    balance numeric(19,4) not null default 0
);
create table if not exists ledger_entries (
    id UUID primary key default gen_random_uuid(),
    account_id UUID not null references accounts(id),
    amount numeric(19,4) not null,
    entry_type varchar(6) not null check(entry_type in (DEBIT, CREDIT)),
    created_at timestampz not null default now()
);