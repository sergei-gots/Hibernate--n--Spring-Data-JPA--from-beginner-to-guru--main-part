-- Section 15 Assignment 17
-- order_service database. initial script
-- Create Users and Grant

-- 1. Clean up server
DROP DATABASE IF EXISTS `order_service_db`;
DROP USER IF EXISTS `orderadmin`@`%`;
DROP USER IF EXISTS `orderuser`@`%`;

-- 2. Create database 'order_service_db'
CREATE DATABASE IF NOT EXISTS `order_service_db`
	CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci; -- ci means case-ignore

-- 3. Create users 'order_service_admin'@'%' and 'order_service_user'@'%'

CREATE USER IF NOT EXISTS `orderadmin`@`%`
	IDENTIFIED WITH caching_sha2_password BY 'password';
CREATE USER IF NOT EXISTS `orderuser`@`%`
	IDENTIFIED WITH caching_sha2_password BY 'password';

-- 3. Grant users with privileges
GRANT
	SELECT, INSERT, UPDATE, DELETE,
	CREATE, ALTER, DROP,
	REFERENCES, INDEX, EXECUTE,
	CREATE VIEW, SHOW VIEW,
	CREATE ROUTINE, ALTER ROUTINE, 
	EVENT, TRIGGER
	ON `order_service_db`.* TO `orderadmin`@`%`;

GRANT
	SELECT, INSERT, UPDATE, DELETE,
	SHOW VIEW 
	ON `order_service_db`.* TO `orderuser`@`%`;

FLUSH PRIVILEGES;
