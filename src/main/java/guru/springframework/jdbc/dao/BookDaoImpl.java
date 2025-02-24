package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by sergei on 21/02/2025
 */
@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Stream<Book> findAllByTitleNotNull() {
        return bookRepository.findByTitleNotNull();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    @Override
    public Book readBookByTitle(String title) {
        return bookRepository.readByTitle(title);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {

        Book persisted = bookRepository.getReferenceById(book.getId());

        persisted.setIsbn(book.getIsbn());
        persisted.setAuthor(book.getAuthor());
        persisted.setPublisher(book.getPublisher());
        persisted.setTitle(book.getTitle());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
