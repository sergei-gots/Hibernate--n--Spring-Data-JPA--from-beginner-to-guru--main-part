CREATE TABLE customer(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    city VARCHAR(50),
    state VARCHAR(30),
    zip_code VARCHAR(10),
    phone VARCHAR(20),
    email VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) engine = InnoDB;


ALTER TABLE order_header
    ADD COLUMN customer_id BIGINT,
    ADD CONSTRAINT fk_order_header_customer FOREIGN KEY(customer_id) REFERENCES customer(id),
    DROP COLUMN customer;