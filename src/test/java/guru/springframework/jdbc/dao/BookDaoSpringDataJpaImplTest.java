package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sergei on 27/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class BookDaoSpringDataJpaImplTest {

    @Autowired
    BookRepository bookRepository;

    BookDao bookDao;

    @BeforeEach
    public void setUp() {
        bookDao = new BookDaoSpringDataJpaImpl(bookRepository);
    }

    @Test
    public void TestFindAll_usingIntLimitAndIntOffset_Page1() {

        int pageSize = 10;

        List<Book> books = bookDao.findAll(pageSize, 0);

        AssertionsForClassTypes.assertThat(books).isNotNull();
        AssertionsForClassTypes.assertThat(books.size()).isEqualTo(pageSize);
    }

    @Test
    public void TestFindAll_Page1() {

        int pageSize = 10;

        Pageable pageable = PageRequest.of(1, pageSize);

        List<Book> books = bookDao.findAll(pageable);

        AssertionsForClassTypes.assertThat(books).isNotNull();
        AssertionsForClassTypes.assertThat(books.size()).isEqualTo(pageSize);
    }

    @Test
    public void TestFindAll_Page2_SortByTitle() {

        int pageSize = 10;

        Pageable pageable = PageRequest.of(1, pageSize);

        List<Book> books = bookDao.findAllSortByTitle(pageable);

        AssertionsForClassTypes.assertThat(books).isNotNull();
        AssertionsForClassTypes.assertThat(books.size()).isEqualTo(pageSize);
    }

    @Test
    public void testGetById() {

        Book book = bookDao.getById(5L);

        assertThat(book).isNotNull();
    }

    @Test
    public void testGetBookByTitle() {

        Book book = bookDao.findBookByTitle("Spring in Action. 5th Edition");

        assertThat(book).isNotNull();
    }

    @Test
    public void testGetBookByTitle_saveAndGet() {

        Book book = new Book();
        book.setIsbn("ISBN-" + RandomString.make(7));
        String title = "Title " + RandomString.make(7);
        book.setTitle(title);

        Book saved = bookDao.save(book);

        Book fetched = bookDao.findBookByTitle(title);

        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(saved.getId());
        assertThat(fetched.getTitle()).isEqualTo(title);
    }

    @Test
    public void testSave() {

        Book book = new Book();
        book.setIsbn("an ISBN");
        book.setTitle("a Title");

        Book saved = bookDao.save(book);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(book.getTitle());

    }

    @Test
    public void testUpdate() {
        Book book = new Book();
        book.setTitle("j-thomson's book");
        book.setIsbn("some-new-ISBN");

        Book persisted = bookDao.save(book);
        persisted.setTitle("John's book");
        persisted.setIsbn("another-ISBN");

        Book updated = bookDao.update(persisted);

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

        Book saved = bookDao.save(book);

        bookDao.deleteById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> bookDao.getById(saved.getId()));
    }
}