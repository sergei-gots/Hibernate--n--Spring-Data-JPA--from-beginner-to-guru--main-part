package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Parameter;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sergei on 21/02/2025
 */
@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book getById(Long id) {
        try(EntityManager em = getEntityManager()) {

        Book book =  em.find(Book.class, id);
        em.close();

        return book;
        }

    }

    @Override
    public List<Book> findAll() {

        try(EntityManager em = getEntityManager()) {

            TypedQuery<Book> query = em.createNamedQuery("find_all_books", Book.class);
            return query.getResultList();
        }
    }

    @Override
    public Book findBookByIsbn(String isbn) {

        try(EntityManager em = getEntityManager()) {

            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
            query.setParameter("isbn", isbn);

            return query.getSingleResult();
        }
    }

    @Override
    public Book findBookByTitle(String title) {

        try (EntityManager em = getEntityManager()) {

            TypedQuery<Book> query = em.createNamedQuery("find_book_by_title", Book.class);
            query.setParameter("title", title);

            return query.getSingleResult();
        }
    }

    @Override
    public Book findBookByTitleWithCriteriaQuery(String title) {

        try (EntityManager em = getEntityManager()){

            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

            Root<Book> bookRoot = criteriaQuery.from(Book.class);

            Parameter<String> titleParameter = criteriaBuilder.parameter(String.class, "title");

            Predicate predicate = criteriaBuilder.equal(bookRoot.get("title"), titleParameter);

            criteriaQuery.select(bookRoot).where(predicate);

            TypedQuery<Book> query = em.createQuery(criteriaQuery);

            query.setParameter(titleParameter, title);

            return query.getSingleResult();

        }
    }

    @Override
    public Book saveNewBook(Book book) {

        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();

            em.persist(book);
            em.flush();
            //Try to comment the next line with 'commit' and you'll see the result with tests-:)
            em.getTransaction().commit();

            return em.find(Book.class, book.getId());
        }

    }

    @Override
    public Book updateBook(Book book) {

        try (EntityManager em = emf.createEntityManager()) {

            em.joinTransaction();
            em.merge(book);
            em.flush();
            em.clear();

            return em.find(Book.class, book.getId());
        }
    }

    @Override
    public void deleteBookById(Long id) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();
        em.close();

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
