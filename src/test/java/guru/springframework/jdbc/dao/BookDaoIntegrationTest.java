package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sergei on 19/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    public void testGetBookById() {

        Book book = bookDao.getById(5L);

        assertThat(book).isNotNull();
        assertThat(book.getId()).isEqualTo(5L);
    }

    @Test
    public void testFindBookByTitle() {

        Book book = bookDao.findBookByTitle("Spring in Action. 5th Edition");

        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Spring in Action. 5th Edition");
    }

    @Test
    public void testSaveNewBook() {

        Book book = new Book();
        book.setAuthorId(13L);
        book.setTitle("Clean Code");
        book.setIsbn("9780136083221");

        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(book.getTitle());
        assertThat(saved.getAuthorId()).isEqualTo(book.getAuthorId());
    }

    @Test
    public void testUpdateBook() {

        Book book = new Book();
        book.setAuthorId(13L);
        book.setTitle("Leave playground cleaner than you found it");
        book.setIsbn("0080136083220");

        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("Clean Code. with illustrations");
        saved.setIsbn("9780136083220");

        Book updated = bookDao.updateBook(saved);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getTitle()).isEqualTo("Clean Code. with illustrations");
        assertThat(updated.getIsbn()).isEqualTo("9780136083220");
        assertThat(updated.getAuthorId()).isEqualTo(book.getAuthorId());
    }

    @Test
    public void testDeleteBookById() {

        Book book = new Book();
        book.setAuthorId(13L);
        book.setTitle("Leave playground cleaner than you found it");
        book.setIsbn("0080136083220");

        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();

    }
}
