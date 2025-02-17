DROP TABLE IF EXISTS book;

CREATE TABLE book (
    id VARCHAR (36) NOT NULL,
    title VARCHAR (255),
    isbn VARCHAR (255) NOT NULL,
    publisher VARCHAR (255),
    author_id VARCHAR (36),
    PRIMARY KEY (id)
) engine = InnoDB;