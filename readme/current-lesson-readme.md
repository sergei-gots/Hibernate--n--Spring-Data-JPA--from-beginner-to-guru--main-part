#### Chapter 15: Paging-n-Sorting
###  Lesson 116
## findAllBooksOrderByTitle() using JdbcTemplate. 

1. We'll add the method use here the interface <code>Pageable</code> and its the most popular implementation 

        List<Book> findAllSortedByTitle(Pageable pageable);

to the <code>BookDao</code> interface.

2. We'll add the implementation:

        @Override
        public List<Book> findAllSortedByTitle(Pageable pageable) {

            Sort.Order sortOrderForTitle = pageable.getSort().getOrderFor("title");

            String sortOrderDirectionForTitle = sortOrderForTitle != null ?
                sortOrderForTitle.getDirection().toString() :
                "";

            String sql = "SELECT * FROM book ORDER BY title " + 
                sortOrderDirectionForTitle + 
                " limit ? offset ?";

              return jdbcTemplate.query(
                      sql,
                      getBookRowMapper(),
                      pageable.getPageSize(),
                      pageable.getOffset()
              );
          }

So, the parameterized sql we use here is:

        SELECT * FROM book ORDER by title DESC limit ? offset ?

    or 

        SELECT * FROM book ORDER by title ASC limit ? offset ?
or 

        SELECT * FORM book ORDER by title limit ? offset ?
 
The last one will sort as it is set by default in the database, usually - ASC 

We get the sorting direction with

        Sort.Order sortOrderForTitle = pageable.getSort().getOrderFor("title");

4. We'll add the test method:


    @Test
    public void testFindAllPage1_SortedByTitle() {

        int pageNumber = 0;
        int pageSize = 10;

        List<Book> books = bookDao.findAllSortedByTitle(
                PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(Sort.Order.desc("title")))
        );

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);

        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(1).getTitle())).isGreaterThan(-1);
        assertThat(books.get(1).getTitle().compareToIgnoreCase(books.get(2).getTitle())).isGreaterThan(-1);
        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(9).getTitle())).isGreaterThan(-1);
    }
   
To add the direction of order we use:

        Sort.by(Sort.Order.desc("title");
or      
        
        Sort.by(Sort.Order.asc("title);
The sort direction by default is 

    Sort.Direction DEFAULT_DIRECTION = Direction.ASC;

And for this case we also can add the next test method:

    @Test
    public void testFindAllPage1_SortedByTitleByDefaultAsc() {

        int pageNumber = 0;
        int pageSize = 10;

        List<Book> books = bookDao.findAllSortedByTitle(
                PageRequest.of(
                        pageNumber,
                        pageSize)
        );

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(pageSize);

        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(1).getTitle())).isLessThan(1);
        assertThat(books.get(1).getTitle().compareToIgnoreCase(books.get(2).getTitle())).isLessThan(1);
        assertThat(books.get(0).getTitle().compareToIgnoreCase(books.get(9).getTitle())).isLessThan(1);
    }
