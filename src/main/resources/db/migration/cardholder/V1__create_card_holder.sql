DROP TABLE IF EXISTS card_holder;

CREATE TABLE card_holder(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    zip_code VARCHAR(10),
    PRIMARY KEY (id)
) engine = InnoDB;