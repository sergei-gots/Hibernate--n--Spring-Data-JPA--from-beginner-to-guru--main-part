package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sergei on 18/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class AuthorDaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    public void testGetAuthorById() {

        Author author = authorDao.getById(12L);

        assertThat(author).isNotNull();
    }

    @Test
    public void testGetAuthorByName() {

        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    public void testSaveNewAuthor() {

        Author author = new Author();
        author.setFirstName("John-Lesson-78");
        author.setLastName("Thompson");

        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getFirstName()).isEqualTo(author.getFirstName());

    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("j");
        author.setLastName("thompson");

        Author persisted = authorDao.saveNewAuthor(author);
        persisted.setFirstName("John");
        persisted.setLastName("Thompson");

        Author updated = authorDao.updateAuthor(persisted);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(persisted.getId());
        assertThat(updated.getFirstName()).isEqualTo("John");
        assertThat(updated.getLastName()).isEqualTo("Thompson");
        assertThat(updated.getCountry()).isEqualTo(author.getCountry());

    }

    @Test
    public void testDeleteAuthorById() {

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(saved.getId()));
    }
}
