package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sergei on 20/02/2025
 */
public class AuthorResultSetExtractor implements ResultSetExtractor<Author> {

    @Override
    public Author extractData(@NonNull ResultSet rs) throws SQLException, DataAccessException {
        return new AuthorRowMapper().mapRow(rs, 0);
    }
}
