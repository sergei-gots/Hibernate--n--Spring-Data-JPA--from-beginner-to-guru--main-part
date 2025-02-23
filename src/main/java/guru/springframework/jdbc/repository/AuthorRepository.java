package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sergei on 18/02/2025
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findByLastNameLike(String lastNamePattern);
}
