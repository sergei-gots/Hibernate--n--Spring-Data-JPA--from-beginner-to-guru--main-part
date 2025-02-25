package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by sergei on 24/02/2025
 */
public class BookRowMapper implements RowMapper<Book> {


    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();

        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublisher(rs.getString("publisher"));
        book.setTitle(rs.getString("title"));
        book.setAuthorId(rs.getLong("author_id"));

        return book;
    }
}
