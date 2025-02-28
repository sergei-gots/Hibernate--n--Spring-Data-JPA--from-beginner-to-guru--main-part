package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Book> findAll(int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM book limit ? offset ?",
                getBookRowMapper(),
                limit, offset
        );
    }

    @Override
    public List<Book> findAll(Pageable pageable) {

        String sql = "SELECT * FROM book " +
                        getOrderForTitle(pageable) +
                        " LIMIT ? OFFSET ?";

        return jdbcTemplate.query(
                sql,
                getBookRowMapper(),
                pageable.getPageSize(),
                pageable.getOffset()
        );
    }


    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getBookRowMapper(), id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", getBookRowMapper(), title);
    }

    @Override
    public Book save(Book book) {

        jdbcTemplate.update(
                "INSERT INTO book (isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId()
        );

        Long createdBookId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdBookId);
    }

    @Override
    public Book update(Book book) {

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
    public void deleteById(Long id) {

        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    private BookRowMapper getBookRowMapper() {
        return new BookRowMapper();
    }

    private String getOrderForTitle(Pageable pageable) {

        Sort.Order sortOrderForTitle = pageable.getSort().getOrderFor("title");

        String sortOrderDirectionForTitle = sortOrderForTitle != null ?
                sortOrderForTitle.getDirection().toString() :
                "";

        return  " ORDER BY title " + sortOrderDirectionForTitle + ' ';

    }
}
