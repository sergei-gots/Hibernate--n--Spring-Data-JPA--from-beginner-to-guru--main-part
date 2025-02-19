package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;

/**
 * Created by sergei on 18/02/2025
 */
public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

}
