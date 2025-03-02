#### Chapter 14: Paging-n-Sorting
## Lesson 122 Spring Data Jpa: Page<T> vs List<T> as returning type

In this commit we'll focus on difference between <code>Page</code> and <code>List</code>
So we'll add some <b>read-</b>named methods in addition to the existed <b>find-</b>named methods:

    public interface AuthorDao {

    ...

            List<Author> findAllByLastName(String lastName, Pageable pageable);

            List<Author> findAllByLastNameLike(String lastName, Pageable pageable);

            Page<Author> readAllByLastNameLike(String lastName, Pageable pageable);

            Page<Author> readAllByLastName(String lastName, Pageable pageable);
    ...

    }