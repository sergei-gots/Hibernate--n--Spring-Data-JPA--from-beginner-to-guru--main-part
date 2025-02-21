package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sergei on 18/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class BookDaoIntegrationTest {
    @Autowired
    BookDao BookDao;

    @Test
    public void testGetBookById() {

        Book Book = BookDao.getById(5L);

        assertThat(Book).isNotNull();
    }

    @Test
    public void testGetBookByTitle() {

        Book Book = BookDao.findBookByTitle("Spring in Action. 5th Edition");

        assertThat(Book).isNotNull();
    }

    @Test
    public void testSaveNewBook() {

        Book book = new Book();
        book.setIsbn("an ISBN");
        book.setTitle("a Title");

        Book saved = BookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(book.getTitle());

    }

    @Test
    public void testUpdateBook() {
        Book Book = new Book();
        Book.setTitle("j-thomson's book");
        Book.setIsbn("some-new-ISBN");

        Book persisted = BookDao.saveNewBook(Book);
        persisted.setTitle("John's book");
        persisted.setIsbn("another-ISBN");

        Book updated = BookDao.updateBook(persisted);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(persisted.getId());
        assertThat(updated.getTitle()).isEqualTo("John's book");
        assertThat(updated.getIsbn()).isEqualTo("another-ISBN");

    }

    @Test
    public void testDeleteBookById() {

        Book Book = new Book();
        Book.setTitle("Book to delete");
        Book.setIsbn("ISBN");

        Book saved = BookDao.saveNewBook(Book);

        BookDao.deleteBookById(saved.getId());

        assertThat(BookDao.getById(saved.getId())).isNull();
    }
}
