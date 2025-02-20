package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *   Created by sergei on 20/02/2025
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {

        return jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
    }


    @Override
    public Author findAuthorByName(String firstName, String lastName) {

        return jdbcTemplate.queryForObject(
                "SELECT * FROM author WHERE first_name = ? AND last_name = ?",
                getRowMapper(),
                firstName, lastName
        );
    }

    @Override
    public Author saveNewAuthor(Author author) {

        jdbcTemplate.update(
                "INSERT INTO author(first_name, last_name, country) VALUES(?, ?, ?)",
                author.getFirstName(),
                author.getLastName(),
                author.getCountry()
        );

        Long savedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(savedId);
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {
    }

    /** This method is applied to get a new instance of RowMapper<Author> in order
     * to provide thread safety
     */
    private RowMapper<Author> getRowMapper() {
        return new AuthorRowMapper();
    }
}
