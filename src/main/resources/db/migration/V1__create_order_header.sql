DROP TABLE IF EXISTS order_header;

CREATE TABLE order_header (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer VARCHAR (255) NOT NULL
) engine = InnoDB;