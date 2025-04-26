-- Section 21 Lesson 186
-- Create 'payment' database. 'Credit Card Project' initial script

-- Symbol '`' used here in MySQl names is called as Backticks.
-- Backticks symbols are used to quote identifiers, such as table names or column names.
-- The symbol ' is called a single quote or apostrophe.
-- Usage in MySQL: single quotes are used to enclose string literals:
-- <code>SELECT * FROM users WHERE name = 'Alice';</code>

-- 1. Clean up server
DROP DATABASE IF EXISTS `payment`;
DROP USER IF EXISTS `paymentadmin`@`%`;
DROP USER IF EXISTS `paymentwpuser`@`%`;

-- 2. Create database 'payment'
CREATE DATABASE IF NOT EXISTS `payment`
	CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci; -- ci means case-ignore

-- 3. Create users 'paymentadmin'@'%' and 'paymentuser'@'%'

CREATE USER IF NOT EXISTS `paymentadmin`@`%`
	IDENTIFIED WITH caching_sha2_password BY 'password';
CREATE USER IF NOT EXISTS `paymentuser`@`%`
	IDENTIFIED WITH caching_sha2_password BY 'password';

-- 3. Grant users with privileges
GRANT
	SELECT, INSERT, UPDATE, DELETE,
	CREATE, ALTER, DROP,
	REFERENCES, INDEX, EXECUTE,
	CREATE VIEW, SHOW VIEW,
	CREATE ROUTINE, ALTER ROUTINE, 
	EVENT, TRIGGER
	ON `payment`.* TO `paymentadmin`@`%`;

GRANT
	SELECT, INSERT, UPDATE, DELETE,
	SHOW VIEW 
	ON `payment`.* TO `paymentuser`@`%`;

FLUSH PRIVILEGES;
