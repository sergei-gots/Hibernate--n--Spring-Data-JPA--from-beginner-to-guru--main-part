package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sergei on 18/02/2025
 */
public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    List<Author> findAll(Pageable pageable);

    List<Author> findAllByLastName(String lastName, Pageable pageable);

    List<Author> findAllByLastNameLike(String lastName, Pageable pageable);

    default Page<Author> readAllByLastNameLike(String lastName, Pageable pageable) {
        return null;
    }

    default Page<Author> readAllByLastName(String lastName, Pageable pageable) {
        return null;
    }

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteById(Long id);
}
