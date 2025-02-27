package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by sergei on 21/02/2025
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Author> findAllByLastNameLike(String lastName, Pageable pageable) {
        return authorRepository.findByLastNameLike(lastName + '%', pageable);
    }

    @Override
    public List<Author> findAllByLastNameSortByFirstName(String lastName, Pageable pageable) {
        return List.of();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {

        Author persisted = authorRepository.getReferenceById(author.getId());

        persisted.setFirstName(author.getFirstName());
        persisted.setLastName(author.getLastName());
        persisted.setCountry(author.getCountry());
        persisted.setBooks(author.getBooks());

        return authorRepository.save(persisted);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
