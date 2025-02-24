package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
    BookDao bookDao;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testGetBookById() {

        Book book = bookDao.getById(5L);

        assertThat(book).isNotNull();
    }

    @Test
    public void testGetAll() {

        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    public void testFindAllByTitleNotNull() {

        AtomicInteger count = new AtomicInteger();
        bookDao.findAllByTitleNotNull()
                .forEach(book -> count.incrementAndGet());

        assertThat(count.get()).isGreaterThan(5);
    }

    @Test
    public void testGetBookByTitle() {

        Book book = bookDao.findBookByTitle("Spring in Action. 5th Edition");

        assertThat(book).isNotNull();
    }

    @Test
    public void testGetBookByTitle_whenThereIsNoMatch() {

        Book book = bookDao.getBookByTitle(null);

        assertThat(book).isNull();
    }

    @Test
    public void findBookByTitleUsingSqlQuery() {

        Book book = bookRepository.findByTitleUsingSqlQuery("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    public void findBookByTitleUsingHqlQuery() {

        Stream<Book> books = bookRepository.findByTitleUsingHqlQuery("Clean Code");

        assertThat(books).isNotNull();
    }

    @Test
    public void testReadBookByTitle_whenThereIsNoMatch_thenThrowsEmptyResultDataAccessException() {

        assertThrows(EmptyResultDataAccessException.class, ()-> bookDao.readBookByTitle(null));
    }

    @Test
    public void  testFutureQueryBooksByTitle() throws ExecutionException, InterruptedException {

        Future<Stream<Book>> futureBooks = bookRepository.queryByTitle("Clean Code");
        assertThat(futureBooks.get().findAny().isPresent()).isTrue();
    }

    @Test
    public void testGetBookByIsbn() {

        Book book = new Book();
        book.setTitle("SpringFramework: from beginner to guru. Lesson 92. TypedQuery<T> vs 'cringe' Query");
        String isbn = "123" + RandomString.make(7);
        book.setIsbn(isbn);

        Book saved = bookDao.saveNewBook(book);

        Book fetched = bookDao.findBookByIsbn(isbn);

        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(saved.getId());
        assertThat(fetched.getIsbn()).isEqualTo(isbn);

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

        assertThrows(JpaObjectRetrievalFailureException.class, ()-> bookDao.getById(saved.getId()));
    }
}
