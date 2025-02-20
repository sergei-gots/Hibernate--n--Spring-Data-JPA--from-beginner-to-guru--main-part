package guru.springframework.jdbc.dao;


import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei on 20/02/2025
 */
public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

        rs.next();

        Author author = new Author();

        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setCountry(rs.getString("country"));

        List<Book> books = new ArrayList<>();
        author.setBooks(books);

            do {
                String isbn = rs.getString("isbn");
                if (isbn == null) {
                    break;
                }

                Book book = new Book();
                book.setIsbn(isbn);
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(author);

                books.add(book);
            } while (rs.next());

        return author;
    }
}
