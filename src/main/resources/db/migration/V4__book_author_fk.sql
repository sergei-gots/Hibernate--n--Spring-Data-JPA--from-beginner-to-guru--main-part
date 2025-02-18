ALTER TABLE book
    ADD CONSTRAINT book_author_fk FOREIGN KEY (author_id) REFERENCES author(id);