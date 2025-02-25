#### Chapter 14: Paging-n-Sorting
###  Lesson 114
## findAllBooks() using JdbcTemplate. Adding Paging

We're providing the method <code>findAll()</code>'s implementation:
    
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", getBookRowMapper());
    }   

Remember, the <code>BookRowMapper</code> is:

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

