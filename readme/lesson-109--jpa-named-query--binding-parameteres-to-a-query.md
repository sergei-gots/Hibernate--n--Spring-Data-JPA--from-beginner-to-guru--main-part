# Spring Data JPA Queries

## Lesson 109. Jpa @NamedQuery Binding named parameters to a @Query

John found this a little quirky to get implemented working,
but it seems like the query name
has to be prefixed with the class name.
Otherwise, thing were not happy for this.

### @NamedQuery Naming Convention

If there is a method named not according to JpaRepository naming conventions,
the Spring will look up @NamedQuery for Entity class which instance the JpaRepository method returns.

And e.g. if we have in our BookRepository the declaration as

	<Stream>Book jpaNamed(String title);

the corresponding @NamedQuery should be declared on the Book class like so:

	@NamedQuery(name = "Book.jpaNamed", query = "FROM Book b WHERE title =:title")

So we declare query name under @NamedQuery on Entity class.
And then in the repository implementation for this, 
the method name has to match the query name.