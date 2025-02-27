package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by sergei on 26/02/2025
 */
public class AuthorDaoJdbcTemplateImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getAuthorRowMapper(), id);
    }

    @Override
    public List<Author> findAll(Pageable pageable) {

        return jdbcTemplate.query(
                "SELECT * FROM author LIMIT ? OFFSET ?",
                getAuthorRowMapper(),
                pageable.getPageSize(),
                pageable.getOffset()
        );
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Author> findAllByLastNameSortByFirstName(String lastName, Pageable pageable) {

        StringBuilder sql = new StringBuilder("SELECT * FROM author WHERE last_name = ? ORDER BY first_name ");

        Sort.Order firstNameSortOrder = pageable.getSort().getOrderFor("firstName");

        if (firstNameSortOrder != null) {
            sql.append(firstNameSortOrder.getDirection().name());
        }
        sql.append(" LIMIT ? OFFSET ?");

        return jdbcTemplate.query(
                sql.toString(),
                getAuthorRowMapper(),
                lastName,
                pageable.getPageSize(),
                pageable.getOffset()
        );

    }

    @Override
    public List<Author> findAllByLastNameLike(String lastName, Pageable pageable) {

        return jdbcTemplate.query(
                "SELECT * FROM author OFFSET ? LIMIT ?",
                getAuthorRowMapper(),
                pageable.getOffset(),
                pageable.getPageSize()
        );
    }

    @Override
    public Author saveNewAuthor(Author author) {

        jdbcTemplate.update(
                "INSERT INTO author(first_name, last_name, country) VALUES(?,?,?)",
                author.getFirstName(), author.getLastName(), author.getCountry()
        );

        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(id);
    }

    @Override
    public Author updateAuthor(Author author) {

        jdbcTemplate.update(
                "UPDATE author SET first_name = ?, last_name = ?, country = ? WHERE id = ?",
                author.getFirstName(), author.getLastName(), author.getCountry(), author.getId()
        );

        return this.getById(author.getId());
    }

    @Override
    public void deleteById(Long id) {

        jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
    }

    private AuthorRowMapper getAuthorRowMapper() {
        return new AuthorRowMapper();
    }
}
