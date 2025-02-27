package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sergei on 21/02/2025
 */
@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    //ToDo
    @Override
    public List<Book> findAllSortByTitle(Pageable pageable) {
        return List.of();
    }

    //ToDo
    @Override
    public List<Book> findAll(int limit, int offset) {
        return List.of();
    }

    //ToDo
    @Override
    public List<Book> findAll(Pageable pageable) {
        return List.of();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .findAny()
                .orElseThrow((EntityNotFoundException::new));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Book book) {

        Book persisted = bookRepository.getReferenceById(book.getId());

        persisted.setIsbn(book.getIsbn());
        persisted.setAuthorId(book.getAuthorId());
        persisted.setPublisher(book.getPublisher());
        persisted.setTitle(book.getTitle());

        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
