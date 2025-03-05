CREATE TABLE order_line (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    quantity_ordered INT NOT NULL,
    order_header_id BIGINT NOT NULL,

    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP,

    CONSTRAINT order_header_fk
        FOREIGN KEY (order_header_id) REFERENCES order_header(id)
) ENGINE = InnoDB;