package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Created by sergei on 18/02/2025
 */
public interface BookDao {

    Book getById(Long id);

    List<Book> findAll();

    List<Book> findAll(int limit, int offset);

    List<Book> findAll(Pageable pageable);

    Book findBookByTitle(String title);

    Book save(Book book);

    Book update(Book book);

    void deleteById(Long id);

}
