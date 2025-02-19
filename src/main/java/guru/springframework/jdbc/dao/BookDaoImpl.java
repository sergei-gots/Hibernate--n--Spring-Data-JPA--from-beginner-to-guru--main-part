package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by sergei on 19/02/2025
 */
@Component
public class BookDaoImpl implements BookDao{

    private final DataSource dataSource;
    private final AuthorDao authorDao;

    @Autowired
    public BookDaoImpl(DataSource dataSource, AuthorDao authorDao) {
        this.dataSource = dataSource;
        this.authorDao = authorDao;
    }

    @Override
    public Book getById(Long id) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
            ps.setLong(1, id);
            ps.executeQuery();
            resultSet = ps.getResultSet();

            if (resultSet.next()) {
                return getBookFromRs(resultSet);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                 closeAll(resultSet, ps, connection);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book findBookByTitle(String title) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book WHERE title = ?");
            ps.setString(1, title);
            ps.executeQuery();
            resultSet = ps.getResultSet();

            if (resultSet.next()) {
                return getBookFromRs(resultSet);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                closeAll(resultSet, ps, connection);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book saveNewBook(Book book) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();
            ps = connection.prepareStatement("INSERT INTO book (isbn, publisher, title, author_id) VALUES(?,?,?,?)");
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getPublisher());
            ps.setString(3, book.getTitle());
            if (book.getAuthorId() != null) {
                ps.setLong(4, book.getAuthorId().getId());
            }
            else {
                ps.setNull(4, Types.BIGINT);
            }
            ps.execute();

            Statement statement = connection.createStatement();
            //Note: the next SQL Statement "SELECT LAST_INSERT_ID()" is MySQL specific.
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");

            if (resultSet.next()) {
                Long savedId = resultSet.getLong(1);
                return this.getById(savedId);
            }

            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                closeAll(resultSet, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book updateBook(Book book) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {

            connection = dataSource.getConnection();
            ps = connection.prepareStatement("UPDATE book SET isbn = ?, publisher = ?, title = ?, author_id = ? WHERE id = ?");
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getPublisher());
            ps.setString(3, book.getTitle());
            if (book.getAuthorId() != null) {
                ps.setLong(4, book.getAuthorId().getId());
            }
            else {
                ps.setNull(4, Types.BIGINT);
            }
            ps.setLong(5, book.getId());
            ps.execute();

       }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                closeAll(null, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return this.getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {

            connection = dataSource.getConnection();
            ps = connection.prepareStatement("DELETE FROM book WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();

       }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                closeAll(null, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private Book getBookFromRs(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getLong("id"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setTitle(resultSet.getString("title"));
        Long authorId = resultSet.getLong("author_id");
        Author author = authorDao.getById(authorId);
        book.setAuthorId(author);

        return book;
    }

    private static void closeAll(ResultSet resultSet, PreparedStatement ps, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (ps != null) {
            ps.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
