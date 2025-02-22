package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.*;
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
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {

        try(EntityManager em = getEntityManager()) {

            TypedQuery<Author> query = em.createNamedQuery("find_all_authors", Author.class);
            return query.getResultList();
        }
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {

        try(EntityManager em = getEntityManager()) {
            TypedQuery<Author> query = em.createNamedQuery("find_author_by_name", Author.class);

            query.setParameter("first_name", firstName);
            query.setParameter("last_name", lastName);

            return query.getSingleResult();
        }
    }

    @Override
    public Author findAuthorByNameWithNativeSqlQuery(String firstName, String lastName) {

        try (EntityManager em = getEntityManager()) {

            Query sqlNativeQuery = em.createNativeQuery(
                    "SELECT * FROM author a WHERE a.first_name = ? AND a.last_name = ?",
                    Author.class
            );

            sqlNativeQuery.setParameter(1, firstName);
            sqlNativeQuery.setParameter(2, lastName);

            return (Author) sqlNativeQuery.getSingleResult();
        }
    }

    @Override
    public Author findAuthorByNameWithCriteriaQuery(String firstName, String lastName) {

        try(EntityManager em = getEntityManager()) {

            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

            Root<Author> authorRoot = criteriaQuery.from(Author.class);

            Parameter<String> firstNameParameter = criteriaBuilder.parameter(String.class, "firstName");
            Parameter<String> lastNameParameter = criteriaBuilder.parameter(String.class, "lastName");

            Predicate firstNamePredicate = criteriaBuilder.equal(authorRoot.get("firstName"), firstNameParameter);
            Predicate lastNamePredicate = criteriaBuilder.equal(authorRoot.get("lastName"), lastNameParameter);

            criteriaQuery
                    .select(authorRoot)
                    .where(criteriaBuilder
                            .and(firstNamePredicate, lastNamePredicate)
                    );

            TypedQuery<Author> query = em.createQuery(criteriaQuery);

            query.setParameter(firstNameParameter, firstName);
            query.setParameter(lastNameParameter, lastName);

            return query.getSingleResult();
        }
    }

    @Override
    public List<Author> findAuthorListByLastNameLike(String lastName) {

        try(EntityManager em = getEntityManager()) {

            TypedQuery<Author> query = em.createQuery(
                    "SELECT a FROM Author a WHERE a.lastName like :last_name",
                    Author.class
            );
            query.setParameter("last_name", lastName + '%');
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

            em.joinTransaction();
            em.merge(author);
            em.flush();
            em.clear();     //this em.clear(); clears the first level cache

            return em.find(Author.class, author.getId());
        }
    }

    @Override
    public void deleteAuthorById(Long id) {

        try (EntityManager em = getEntityManager()) {

            em.joinTransaction();
            Author author = em.find(Author.class, id);
            em.remove(author);
            em.flush();
            em.getTransaction().commit();
        }
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
