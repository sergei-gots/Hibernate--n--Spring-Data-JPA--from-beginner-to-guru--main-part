DELETE FROM book;
DELETE FROM author;

-- Books by Craig Walls
INSERT INTO author (first_name, last_name) VALUES ('Craig', 'Walls');
INSERT INTO book (isbn, publisher, title, author_id)
    VALUES
        ('978-1617294945', 'Simon & Schuster', 'Spring in Action. 5th Edition',
            (SELECT id FROM author WHERE first_name = 'Craig' and last_name = 'Walls')),
        ('978-1617292545', 'Simon & Schuster', 'Spring in Action. 1th Edition',
            (SELECT id FROM author WHERE first_name = 'Craig' and last_name = 'Walls')),
        ('978-1617297571', 'Simon & Schuster', 'Spring in Action. 6th Edition',
            (SELECT id FROM author WHERE first_name = 'Craig' and last_name = 'Walls'));

-- A book by Eric Evans
INSERT INTO author (first_name, last_name) VALUES ('Eric', 'Evans');
INSERT INTO book (isbn, publisher, title, author_id)
    VALUES ('978-0321125217', 'Addison Wesley', 'Domain-Driven Design',
        (SELECT id FROM author WHERE first_name = 'Eric' and last_name = 'Evans'));

-- A book by Robert Martin
INSERT INTO author (first_name, last_name) VALUES ('Robert', 'Martin');
INSERT INTO book (isbn, publisher, title, author_id)
    VALUES ('978-0134494166', 'Addison Wesley', 'Domain-Driven Design',
        (SELECT id FROM author WHERE first_name = 'Robert' and last_name = 'Martin'));

