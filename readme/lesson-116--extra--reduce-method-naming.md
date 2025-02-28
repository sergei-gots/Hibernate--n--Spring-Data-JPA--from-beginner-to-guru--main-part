#### Chapter 14: Paging-n-Sorting
###  Lesson 116. Assignment 15. Extra

In this commit we'll complete implementation for the next methods:

    public interface AuthorDao {
        ...
        Author getById(Long id);
        
        Author save(Author author);
        Author update(Author author);
    
        Author deleteById()
        ...
    }   

for the class <code>AuthorDaoJdbcTemplateImpl</code>

Note: since this commit we will use shorter but clearer naming for these methods,
e.g. we will use

    save

instead of

    saveNewAuthor

or 

    deleteById

instead of

    deleteAuthorById

etc.
