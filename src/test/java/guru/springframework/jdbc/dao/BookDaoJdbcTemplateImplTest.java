package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    JdbcTemplate jdbcTemplate;

    BookDao bookDao;

    @BeforeEach
    public void setUp() {
        bookDao = new BookDaoJdbcTemplateImpl(jdbcTemplate);
    }

    @Test
    public void testGetAll() {

        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    public void testGetPage1() {

        int limit = 10;
        int offset = 0;

        List<Book> books = bookDao.findAll(limit, offset);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(limit);
    }
    @Test
    public void testGetPage2() {

        int limit = 10;
        int offset = 10;

        List<Book> books = bookDao.findAll(limit, offset);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(limit);
    }

    @Test
    public void testGetPage100() {

        int limit = 10;
        int offset = 990;

        List<Book> books = bookDao.findAll(limit, offset);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    public void testFindAllPage1_UsingPageable() {

        int pageNumber = 0;
        int pageSize = 10;

        List<Book> books = bookDao.findAll(PageRequest.of(pageNumber, pageSize));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);
    }

    @Test
    public void testFindAllPage2_UsingPageable() {

        int pageNumber = 1;
        int pageSize = 10;

        List<Book> books = bookDao.findAll(PageRequest.of(pageNumber, pageSize));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);
    }

    @Test
    public void testFindAllPage100_UsingPageable() {

        int pageNumber = 99;
        int pageSize = 10;

        List<Book> books = bookDao.findAll(PageRequest.of(pageNumber, pageSize));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    public void testFindAllPage1_SortedByTitleDesc() {

        int pageNumber = 0;
        int pageSize = 10;

        List<Book> books = bookDao.findAllSortedByTitle(
                PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(Sort.Order.desc("title")))
        );

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);

        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(1).getTitle())).isGreaterThan(-1);
        assertThat(books.get(1).getTitle().compareToIgnoreCase(books.get(2).getTitle())).isGreaterThan(-1);
        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(9).getTitle())).isGreaterThan(-1);
    }

    @Test
    public void testFindAllPage1_SortedByTitleByDefaultAsc() {

        int pageNumber = 0;
        int pageSize = 10;

        List<Book> books = bookDao.findAllSortedByTitle(
                PageRequest.of(
                        pageNumber,
                        pageSize)
        );

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);

        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(1).getTitle())).isLessThan(1);
        assertThat(books.get(1).getTitle().compareToIgnoreCase(books.get(2).getTitle())).isLessThan(1);
        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(9).getTitle())).isLessThan(1);
    }


    @Test
    public void testGetBookById() {

        Book book = bookDao.getById(5L);

        assertThat(book).isNotNull();
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