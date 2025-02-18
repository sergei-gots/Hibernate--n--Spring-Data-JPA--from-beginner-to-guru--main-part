package guru.springframework.jdbc;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sergei on 18/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorJpaSliceTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void bookJpaSliceTest() {

        Author author = new Author("S", "G", "Some Country");

        long countBefore = authorRepository.count();
        Author saved = authorRepository.save(author);
        long countAfter = authorRepository.count();

        assertThat(countAfter).isEqualTo(countBefore + 1);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();

        Author fetched = authorRepository.getReferenceById(saved.getId());

        assertThat(fetched).isNotNull();
        assertThat(fetched).isEqualTo(saved);
    }
}
