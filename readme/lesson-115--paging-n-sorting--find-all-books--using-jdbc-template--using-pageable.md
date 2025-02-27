#### Chapter 14: Paging-n-Sorting
###  Lesson 115
## findAllBooks() using JdbcTemplate. Paging using Pageable<T>

We will use here the interface <code>Pageable</code> and its the most popular implementation 
<code>PageRequest extends AbstractPageRequest implements Pageable</code>

    org.springframework.data.domain.Pageable

We will introduce one more overloading of <code>findAll</code> to the <code>BookDao</code> class:

    List<Book> findAll(Pageable pageable);

And our implementation with <code>JdbcTemplate</code> will have the next look:

    @Override 
    public List<Book> findAll(Pageable pageable) {
        
        return jdbcTemplate.query(
            "SELECT * FROM book limit ? offset ?",
            getBookRowMapper(),
            pageable.getPageSize(),
            pageable, getOffset()
        );
    }

To test that we will add the methods <code>testFindAllPageN_UsingPageable()</code>, e.g.:

    @Test
    public void testFindAllPage1_UsingPageable() {
        
        int pageNumber = 0;
        int pageSize = 10;        

        List<Book> books = bookDao.findAll(PageRequest.of(pageNumber, pageSize));
        
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);
    }
   
We should understand that <code>pageNumber</code> is not human-accepted 'one-based' but java-accepted zero-based.