package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 18/02/2025
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
