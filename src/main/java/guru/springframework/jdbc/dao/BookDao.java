package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Created by sergei on 18/02/2025
 */
public interface BookDao {

    List<Book> findAll();

    List<Book> findAll(int limit, int offset);

    List<Book> findAll(Pageable pageable);

    Book getById(Long id);

    Book findAnyByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
