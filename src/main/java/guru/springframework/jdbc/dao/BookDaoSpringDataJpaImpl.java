package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sergei on 21/02/2025
 */
@Component
public class BookDaoSpringDataJpaImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoSpringDataJpaImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAll(int limit, int offset) {
        return findAll(PageRequest.of(offset/limit, limit));
    }


    @Override
    public List<Book> findAll(Pageable pageable) {

        Page<Book> bookPage = bookRepository.findAll(pageable);

        return bookPage.getContent();
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
