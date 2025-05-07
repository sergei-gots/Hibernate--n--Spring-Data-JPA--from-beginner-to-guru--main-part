DROP TABLE IF EXISTS credit_card;

CREATE TABLE credit_card (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cvv VARCHAR(20),
    expiration_date VARCHAR(20),
    PRIMARY KEY (id)
) engine = InnoDB;