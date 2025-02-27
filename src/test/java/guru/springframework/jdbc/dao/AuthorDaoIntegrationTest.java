package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sergei on 18/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
@ActiveProfiles("local")
public class AuthorDaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    public void testGetAuthorById() {

        Author author = authorDao.getById(42L);

        assertThat(author).isNotNull();
    }

    @Test
    public void testGetAll() {

        List<Author> authors = authorDao.findAll(Pageable.ofSize(10));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    public void testGetAuthorById_whenNoBooksForAuthor() {

        Author author = authorDao.getById(12L);

        assertThat(author).isNotNull();
    }

    @Test
    public void testGetAuthorByName() {

        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }

    @Test
    public void testGetAuthorByName_whenThereIsNoMatch_thenThrows() {

        assertThrows(EntityNotFoundException.class, () -> authorDao.findAuthorByName("foo", "bar"));
    }

    @Test
    public void testGetAuthorsByLastNameLike() {

        List<Author> authors = authorDao.findAllByLastNameLike("wall", Pageable.unpaged());

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
        assertThat(authors.get(0).getLastName()).isEqualTo("Walls");
    }

    @Test
    public void testSaveNewAuthor() {

        Author author = new Author();
        author.setFirstName("John-Lesson-87-Hibernate");
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

        Author persistent = authorDao.saveNewAuthor(author);
        persistent.setFirstName("John");
        persistent.setLastName("Thompson");

        Author updated = authorDao.updateAuthor(persistent);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(persistent.getId());
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

        assertThrows(JpaObjectRetrievalFailureException.class, () -> authorDao.getById(saved.getId()));
    }
}
