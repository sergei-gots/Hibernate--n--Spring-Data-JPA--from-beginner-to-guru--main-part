package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sergei on 21/02/2025
 */
@Component
public class BookDaoImpl implements BookDao {

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }
}
