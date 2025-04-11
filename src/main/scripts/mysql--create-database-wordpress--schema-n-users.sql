-- Section 20 Lesson 174
-- Create 'word press' database. initial script
-- Create Users and Grant

--- Symbol '`' used here in MySQl names is called as Backticks.
--- Again, backticks symbols are used to quote identifiers, such as table names or column names.
--- The symbol ' is called a single quote or apostrophe.
--- <p>Usage in MySQL: single quotes are used to enclose string literals:
--- <p> <code>SELECT * FROM users WHERE name = 'Alice';</code>

-- 1. Clean up server
DROP DATABASE IF EXISTS `wordpress`;
DROP USER IF EXISTS `wpadmin`@`%`;
DROP USER IF EXISTS `wpuser`@`%`;

-- 2. Create database 'wordpress'
CREATE DATABASE IF NOT EXISTS `wordpress`
	CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci; -- ci means case-ignore

-- 3. Create users 'wpadmin'@'%' and 'wp_user'@'%'

CREATE USER IF NOT EXISTS `wpadmin`@`%`
	IDENTIFIED WITH caching_sha2_password BY 'password';
CREATE USER IF NOT EXISTS `wpuser`@`%`
	IDENTIFIED WITH caching_sha2_password BY 'password';

-- 3. Grant users with privileges
GRANT
	SELECT, INSERT, UPDATE, DELETE,
	CREATE, ALTER, DROP,
	REFERENCES, INDEX, EXECUTE,
	CREATE VIEW, SHOW VIEW,
	CREATE ROUTINE, ALTER ROUTINE, 
	EVENT, TRIGGER
	ON `wordpress`.* TO `wpadmin`@`%`;

GRANT
	SELECT, INSERT, UPDATE, DELETE,
	SHOW VIEW 
	ON `wordpress`.* TO `wpuser`@`%`;

FLUSH PRIVILEGES;
