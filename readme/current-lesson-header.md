# Spring Data JPA Queries

## Lesson 106. Declaring Queries using @Query

Using HQL Query:
    
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Stream<Book> findByTitleUsingHqlQuery(String title);

Using SQL Query:

    @Query(nativeQuery = true,  value = "SELECT * FROM book b WHERE b.title = :title LIMIT 1")
    Book findByTitleUsingSqlQuery(String title);
