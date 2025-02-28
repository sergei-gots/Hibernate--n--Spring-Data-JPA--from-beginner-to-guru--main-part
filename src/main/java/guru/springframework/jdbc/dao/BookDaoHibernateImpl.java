package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sergei on 27/02/2025
 */
public class BookDaoHibernateImpl implements BookDao{

    private final EntityManagerFactory emf;

    public BookDaoHibernateImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Book> findAll() {

        try (EntityManager em = getEntityManager()) {
            TypedQuery<Book> query = em.createQuery("FROM Book", Book.class);
            return query.getResultList();
        }
    }

    @Override
    public List<Book> findAll(int limit, int offset) {

        try (EntityManager em = getEntityManager()) {

            TypedQuery<Book> query = em.createQuery("FROM Book LIMIT :limit OFFSET :offset", Book.class);

            query.setParameter("limit", limit);
            query.setParameter("offset", offset);

            return query.getResultList();
        }
    }

    @Override
    public List<Book> findAll(Pageable pageable) {

        long offset = pageable.getOffset();
        if (offset > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Offset is too large for setFirstResult");
        }

        StringBuilder hql = new StringBuilder("FROM Book ");

        StringBuilder orderClause = new StringBuilder();
        pageable.getSort().get()
                .forEach((order) -> {
                    orderClause.append(orderClause.isEmpty() ? " ORDER BY " : ", ");
                    orderClause.append(order.getProperty());
                    orderClause.append(' ');
                    orderClause.append(order.getDirection());
                });

        hql.append(orderClause);

        try (EntityManager em = getEntityManager()) {

            TypedQuery<Book> query = em.createQuery(hql.toString(), Book.class);

            query.setMaxResults(pageable.getPageSize());
            query.setFirstResult((int) offset);

            return query.getResultList();
        }
    }

    @Override
    public Book getById(Long id) {

        try (EntityManager em = getEntityManager()) {

            Book book = em.find(Book.class, id);

            if (book == null) {
                throw new EntityNotFoundException("Book with id " + id + " is not found in the database");
            }

            return book;
        }
    }

    @Override
    public Book findBookByTitle(String title) {

        try (EntityManager em = getEntityManager()) {

            TypedQuery<Book> query = em.createQuery("FROM Book WHERE title = :title", Book.class);

            query.setParameter("title", title);

            return query.getSingleResult();
        }
    }

    @Override
    public Book save(Book book) {

        try (EntityManager em = getEntityManager()) {

            em.getTransaction().begin();
            em.persist(book);
            em.flush();
            em.getTransaction().commit();
            return book;
        }
    }

    @Override
    public Book update(Book book) {

        try (EntityManager em = getEntityManager()) {

            em.getTransaction().begin();
            em.merge(book);
            em.flush();
            em.clear();
            Book updated = em.find(Book.class, book.getId());
            em.getTransaction().commit();

            return updated;
        }
    }

    @Override
    public void deleteById(Long id) {

            try (EntityManager em = getEntityManager()) {

                em.getTransaction().begin();
                Book book = em.find(Book.class, id);
                em.remove(book);
                em.getTransaction().commit();
            }

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
