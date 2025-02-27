#### Chapter 14: Paging-n-Sorting
###  Lesson 116. Assignment 15.
## findAuthorByLastName() using JdbcTemplate. 

What we need to do here is to add the method

      AuthorDao {
         ...
         
         List<Author> findAuthorsByLastNameSortByFirstName(String lastName, Pageable pageable);
         
         ...
      }

And to right a set of Integration Tests to check the implementations of that.
For testing purposes we'll add the migration

[V6__insert_authors_with_lastname_smith__assignment_15.sql](../src/main/resources/db/migration/V6__insert_authors_with_lastname_smith__assignment_15.sql)
   

This migration will add <b>40 authors</b> with the last name <b>"Smith"</b>

Also we'll rebase the <code>findAll()</code> and some other methods of the interface <code>AuthorDao</code> 
to accept additionally the <code>Pageable pageable</code> argument.