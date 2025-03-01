package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sergei on 01/03/2025
 */
public class AuthorDaoHibernateImpl implements AuthorDao {
    
    private final EntityManagerFactory emf;

    public AuthorDaoHibernateImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getById(Long id) {
        
        try (EntityManager em = getEntityManager()) {
            Author author = em.find(Author.class, id);
            if (author == null) {
                throw new EntityNotFoundException("Author wit id = " + id + " is not found in the table 'author'");
            }
            return author;
        }
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        
        try (EntityManager em = getEntityManager()) {
            
            TypedQuery<Author> query = em.createQuery(
                    "FROM Author WHERE firstName = :firstName AND lastName = :lastName", 
                    Author.class
            );
            
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            
            return query.getSingleResult();
            
        }
    }

    @Override
    public List<Author> findAll(Pageable pageable) {
        
        try (EntityManager em = getEntityManager()) {
         
            String hql = "FROM Author";
            hql += getSortOrder(pageable);
            
            TypedQuery<Author> query = em.createQuery(hql, Author.class);
            
            query.setMaxResults(pageable.getPageSize());
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            
            return query.getResultList();
        }
    }

    @Override
    public List<Author> findAllByLastName(String lastName, Pageable pageable) {
        
        return this.findAllByLastNameLikeOrEquals(lastName, false, pageable);
    }

    @Override
    public List<Author> findAllByLastNameLike(String lastName, Pageable pageable) {
        return this.findAllByLastNameLikeOrEquals(lastName, true, pageable);
    }

    private List<Author> findAllByLastNameLikeOrEquals(String lastName, boolean lastNameLikeFlag, Pageable pageable) {

        try (EntityManager em = getEntityManager()) {

            String hql = "FROM Author WHERE lastName ";
            hql += lastNameLikeFlag ? " LIKE :lastName " : " = :lastName";
            hql += getSortOrder(pageable);

            TypedQuery<Author> query = em.createQuery(hql, Author.class);

            query.setParameter("lastName", lastName);

            query.setMaxResults(pageable.getPageSize());
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));

            return query.getResultList();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {

        try (EntityManager em = getEntityManager()) {

            em.getTransaction().begin();
            em.persist(author);
            em.flush();
            em.getTransaction().commit();

            return author;
        }
    }

    @Override
    public Author updateAuthor(Author author) {

        try (EntityManager em = getEntityManager()) {

            em.getTransaction().begin();
            em.merge(author);
            em.flush();
            em.clear();
            Author merged = em.find(Author.class, author.getId());
            em.getTransaction().commit();

            return merged;
        }
    }

    @Override
    public void deleteById(Long id) {

        try (EntityManager em = getEntityManager()) {

            em.getTransaction().begin();
            Author author = em.find(Author.class, id);
            em.remove(author);
            em.getTransaction().commit();
        }

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private String getSortOrder(Pageable pageable) {

        StringBuilder sql = new StringBuilder();

        pageable.getSort().get().forEach((order) -> {
            sql.append (sql.isEmpty() ? " ORDER BY " : "', ");
            sql.append(order.getProperty());
            sql.append(' ');
            sql.append(order.getDirection().name());
        });

        return sql.toString();
    }
    
}
