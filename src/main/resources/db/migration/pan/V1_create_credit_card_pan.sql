DROP TABLE IF EXISTS credit_card_pan;

CREATE TABLE credit_card_pan (
    id BIGINT NOT NULL AUTO_INCREMENT,
    credit_card_number VARCHAR(30),
    PRIMARY KEY (id)
) engine = InnoDB;