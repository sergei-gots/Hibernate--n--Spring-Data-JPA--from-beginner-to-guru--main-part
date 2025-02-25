#### Chapter 14: Paging-n-Sorting
###  Lesson 114
## findAllBooks() using JdbcTemplate. Adding Paging

What we're doing here is adding an overloading for the method <code>findAll()</code> to our <code>BookDao</code> class:

    List<Book> findAll(int limit, int offset)

And then we're implementing that:

    @Override
    public List<Book> findAll(int limit, int offset) {
        return jdbcTemplate.query(
            "SELECT * FROM book limit ? offset ?", 
            getBookRowMapper(),
            limit, offset
        );
    }   

So, the SQL Statement is becoming the next:

    SELECT * FROM book limit ? offset ?;

Also, we're performing the next test cases for that:

    @Test
    public void testGetPage1() {

        int limit = 10;
        int offset = 0;

        List<Book> books = bookDao.findAll(limit, offset);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(limit);
    }
    @Test
    public void testGetPage2() {

        int limit = 10;
        int offset = 10;

        List<Book> books = bookDao.findAll(limit, offset);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(limit);
    }

    @Test
    public void testGetPage100() {

        int limit = 10;
        int offset = 990;

        List<Book> books = bookDao.findAll(limit, offset);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

That will be made in assumption, that there are ca. 100 records in the table <code>book</code> in our test database.