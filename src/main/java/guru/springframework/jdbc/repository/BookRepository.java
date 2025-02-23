package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sergei on 18/02/2025
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);
}
