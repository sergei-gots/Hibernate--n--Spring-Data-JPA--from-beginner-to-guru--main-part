package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

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
        EntityManager em = getEntityManager();

        Book book =  em.find(Book.class, id);
        em.close();

        return book;
    }

    @Override
    public Book findBookByTitle(String title) {

        EntityManager em = getEntityManager();

        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :title ", Book.class);
        query.setParameter("title", title);

        Book book =  query.getSingleResult();

        em.close();

        return book;
    }

    @Override
    public Book saveNewBook(Book book) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();

        Book persistent = em.find(Book.class, book.getId());

        em.close();

        return persistent;
    }

    @Override
    public Book updateBook(Book book) {

        EntityManager em = getEntityManager();

        em.joinTransaction();
        em.merge(book);
        em.flush();
        em.clear();

        Book persistent = em.find(Book.class, book.getId());

        em.close();

        return persistent;
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
