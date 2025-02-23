package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sergei on 21/02/2025
 */
@Component
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
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
