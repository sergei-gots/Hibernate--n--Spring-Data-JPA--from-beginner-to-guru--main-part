
In order to get known the current isolation level in the database
we can exectute the next SQL-statements:

📌 MySQL / MariaDB

SELECT @@tx_isolation;  -- For MySQL < 8.0
SELECT @@transaction_isolation;  -- For MySQL 8.0+

📌 PostgreSQL

SHOW TRANSACTION ISOLATION LEVEL;

📌 Oracle

SELECT SYS_CONTEXT('USERENV', 'ISOLATION_LEVEL') FROM dual;

📌 SQL Server

DBCC USEROPTIONS;  -- all the current params incl. isolation level

📌 SQLite

PRAGMA read_uncommitted;

(0 = SERIALIZABLE, 1 = READ UNCOMMITTED)
