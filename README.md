
# Double ledger entry system for transaction

> “Double-entry systems are more reliable at tracking money than any other viable alternative. When software fails to track money properly, it does so in a number of common patterns. Internal records differing from bank statements, reconciliation engines gone awry, balances that don’t make sense given a set of transactions—these are all problems that can be mitigated with double-entry accounting. The core principle of double-entry accounting is that every transaction should record both where the money came from and what the money was used for.”

From [Modern Treasure](https://www.moderntreasury.com/journal/accounting-for-developers-part-i)

## about this demo
An implementation of a double ledger entry system, designed for atomicity and eventual consistency.\
Transactions are published to Apache Kafka with the outbox pattern. 

