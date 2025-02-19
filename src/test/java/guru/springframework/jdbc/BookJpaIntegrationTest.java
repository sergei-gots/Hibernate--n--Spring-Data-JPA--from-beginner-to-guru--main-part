package guru.springframework.jdbc;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
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
public class BookJpaIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void bookJpaIntegrationTest() {

        Book book = new Book();
        book.setIsbn("an Isbn");
        book.setPublisher("A Publisher");
        book.setTitle("A Title");

        long countBefore = bookRepository.count();
        Book saved = bookRepository.save(book);
        long countAfter = bookRepository.count();

        assertThat(countAfter).isEqualTo(countBefore + 1);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();

        Book fetched = bookRepository.getReferenceById(saved.getId());

        assertThat(fetched).isNotNull();
        assertThat(fetched).isEqualTo(saved);
    }
}
