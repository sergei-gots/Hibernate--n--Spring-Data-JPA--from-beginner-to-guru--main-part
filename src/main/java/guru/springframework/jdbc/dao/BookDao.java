package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;

/**
 * Created by sergei on 18/02/2025
 */
public interface BookDao {

    Book getById(Long id);

    Book findAnyByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
