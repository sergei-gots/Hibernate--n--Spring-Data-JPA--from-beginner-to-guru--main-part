package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.stream.Stream;

/**
 * Created by sergei on 18/02/2025
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Stream<Book> findByTitle(@Nullable String title);

}
