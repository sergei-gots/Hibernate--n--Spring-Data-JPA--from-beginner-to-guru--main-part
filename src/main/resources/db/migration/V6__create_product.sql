CREATE TABLE product(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    description VARCHAR(63),
    product_status VARCHAR(31) NOT NULL,

    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP

) engine = InnoDB;