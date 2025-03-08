CREATE TABLE order_approval (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    approved_by VARCHAR(50) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) engine = InnoDB;

ALTER TABLE order_header
    ADD COLUMN order_approval_id BIGINT,
    ADD CONSTRAINT fk_order_header_order_approval
        FOREIGN KEY (order_approval_id) REFERENCES order_approval (id);