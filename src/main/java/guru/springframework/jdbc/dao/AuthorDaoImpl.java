package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

/**
 *   Created by sergei on 20/02/2025
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    private final static String SQL_GET_AUTHOR_LEFT_OUTER_JOIN_BOOK_BASE =
        """
            SELECT author.*, book.id as book_id, book.title, book.isbn, book.publisher
            FROM author
            LEFT OUTER JOIN book ON book.author_id = author.id
        """;

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {


        return jdbcTemplate.query(
                SQL_GET_AUTHOR_LEFT_OUTER_JOIN_BOOK_BASE + " WHERE author.id = ?",
                getAuthorResultSetExtractor(),
                id);
    }


    @Override
    public Author findAuthorByName(String firstName, String lastName) {

        return jdbcTemplate.query(
                SQL_GET_AUTHOR_LEFT_OUTER_JOIN_BOOK_BASE + " WHERE first_name = ? AND last_name = ?",
                getAuthorResultSetExtractor(),
                firstName, lastName
        );
    }

    @Override
    public Author saveNewAuthor(Author author) {

        jdbcTemplate.update(
                "INSERT INTO author(first_name, last_name, country) VALUES(?, ?, ?)",
                author.getFirstName(), author.getLastName(), author.getCountry()
        );

        Long savedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(savedId);
    }

    @Override
    public Author updateAuthor(Author author) {

        jdbcTemplate.update("UPDATE author SET first_name = ?, last_name = ?, country = ? WHERE id = ?",
                author.getFirstName(), author.getLastName(), author.getCountry(), author.getId()
        );

        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {

        jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
    }

    /** This method is applied to get a new instance of ResultSetExtractor<Author> in order
     * to provide thread safety
     */
    private ResultSetExtractor<Author> getAuthorResultSetExtractor() {
        return new AuthorResultSetExtractor();
    }
}
