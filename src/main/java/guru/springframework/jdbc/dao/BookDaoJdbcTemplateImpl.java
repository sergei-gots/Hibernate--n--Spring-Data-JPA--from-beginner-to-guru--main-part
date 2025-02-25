package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by sergei on 24/02/2025
 */
public class BookDaoJdbcTemplateImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", getBookRowMapper());
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getBookRowMapper(), id);
    }

    @Override
    public Book findAnyByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", getBookRowMapper(), title);
    }

    @Override
    public Book saveNewBook(Book book) {

        jdbcTemplate.update(
                "INSERT INTO book (isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId()
        );

        Long createdBookId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdBookId);
    }

    @Override
    public Book updateBook(Book book) {

        Long bookId = book.getId();

        Long authorId = book.getAuthorId();
        if (authorId == 0L) {
            authorId = null;
        }

        jdbcTemplate.update(
                "UPDATE book SET isbn = ?, publisher = ?, title = ?, author_id = ? WHERE id = ?",
                book.getIsbn(), book.getPublisher(), book.getTitle(), authorId, bookId
        );

        return this.getById(bookId);
    }

    @Override
    public void deleteBookById(Long id) {

        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    private BookRowMapper getBookRowMapper() {
        return new BookRowMapper();
    }
}
