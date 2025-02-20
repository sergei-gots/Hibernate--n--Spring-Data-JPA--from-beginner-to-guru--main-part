package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *   Created by sergei on 20/02/2025
 */
@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {

        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id  = ?", getBookRowMapper(), id);
    }

    @Override
    public Book findBookByTitle(String title) {

        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE title  = ?", getBookRowMapper(), title);
    }

    @Override
    public Book saveNewBook(Book book) {

        Author author = book.getAuthor();

        jdbcTemplate.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES(?, ?, ?, ?)",
         book.getIsbn(), book.getPublisher(), book.getTitle(),
                author != null ? author.getId() : null
        );

        Long savedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(savedId);
    }

    @Override
    public Book updateBook(Book book) {

        Author author = book.getAuthor();

        jdbcTemplate.update("UPDATE book SET isbn = ?, publisher = ?, title = ?, author_id = ? WHERE id = ?",
                book.getIsbn(), book.getPublisher(), book.getTitle(),
                author != null ? author.getId() : null,
                book.getId()
        );

        return this.getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {

        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);

    }

    private RowMapper<Book> getBookRowMapper() {
        return new BookRowMapper();
    }

}
