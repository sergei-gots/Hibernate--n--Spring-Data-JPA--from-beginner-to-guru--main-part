package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sergei on 24/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
class BookDaoJdbcTemplateImplTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    BookDao bookDao;

    @BeforeEach
    public void setUp() {
        bookDao = new BookDaoJdbcTemplateImpl(jdbcTemplate);
    }

    @Test
    public void testGetBookById() {

        Book book = bookDao.getById(5L);

        assertThat(book).isNotNull();
    }

    @Test
    public void testGetAll() {

        List<Book> books = bookRepository.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    public void testGetBookByTitle() {

        Book book = bookDao.findAnyByTitle("Spring in Action. 5th Edition");

        assertThat(book).isNotNull();
    }

    @Test
    public void testGetBookByTitle_whenThereIsNoBooksWithNoTitle() {

        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.findAnyByTitle(null));
    }

    @Test
    public void testSaveNewBook() {

        Book book = new Book();
        book.setIsbn("an ISBN");
        book.setTitle("a Title");

        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(book.getTitle());

    }

    @Test
    public void testUpdateBook() {

        Book book = new Book();
        book.setTitle("j-thomson's book");
        book.setIsbn("some-new-ISBN");

        Book persisted = bookDao.saveNewBook(book);
        persisted.setTitle("John's book");
        persisted.setIsbn("another-ISBN");

        Book updated = bookDao.updateBook(persisted);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(persisted.getId());
        assertThat(updated.getTitle()).isEqualTo("John's book");
        assertThat(updated.getIsbn()).isEqualTo("another-ISBN");

    }

    @Test
    public void testDeleteBookById() {

        Book book = new Book();
        book.setTitle("Book to delete");
        book.setIsbn("ISBN");

        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(saved.getId()));
    }

}