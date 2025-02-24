# Spring Data JPA Queries

## Lesson 107. Binding named parameters to a @Query

When the parameter passed in the method differs by name with
the corresponding parameters, we will apply @Param annotation 
to that method parameter binding it so with the query parameter.

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Stream<Book> findByTitleUsingHqlQuery(@Param("title") String bookTitle);