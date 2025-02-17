DROP TABLE IF EXISTS author;

CREATE TABLE author (
    id VARCHAR (36) NOT NULL,
    first_name VARCHAR (255),
    last_name VARCHAR (255),
    country VARCHAR (63),
    PRIMARY KEY (id)
) engine = InnoDB;