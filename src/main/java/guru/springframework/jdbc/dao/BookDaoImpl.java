package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Stub implementation
    @Override
    public List<Book> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public Book findAnyByTitle(String title) {
        return bookRepository.findByTitle(title)
                .findAny()
                .orElseThrow((EntityNotFoundException::new));
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
        persisted.setAuthorId(book.getAuthorId());
        persisted.setPublisher(book.getPublisher());
        persisted.setTitle(book.getTitle());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
