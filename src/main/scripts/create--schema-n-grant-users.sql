-- LESSON 61
-- bookdb2 database. initial script
-- Create Users and Grant
DROP DATABASE IF EXISTS `bookdb2`;

CREATE DATABASE IF NOT EXISTS `bookdb2`
	CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci; -- ci means case-ignore

GRANT
	SELECT, INSERT, UPDATE, DELETE,
	CREATE, ALTER, DROP,
	REFERENCES, INDEX, EXECUTE,
	CREATE VIEW, SHOW VIEW,
	CREATE ROUTINE, ALTER ROUTINE, 
	EVENT, TRIGGER
	ON `bookdb2`.* TO `bookadmin`@`%`;

GRANT
	SELECT, INSERT, UPDATE, DELETE,
	SHOW VIEW 
	ON `bookdb2`.* TO `bookuser`@`%`;

FLUSH PRIVILEGES;
