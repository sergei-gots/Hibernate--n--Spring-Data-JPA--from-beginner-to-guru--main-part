#### Chapter 14: Paging-n-Sorting
## Lesson 121 Sorting with Spring Data Jpa


We will introduce again the method <code>List<Book> findAllSortByTitle(Pageable pageable)</code>.For clarity, we will refactor the class <code>BookDaoImpl</code> with renaming to <code>BookDaoSpringDataJpaImpl</code>
It will have the default implementation and will just replace the existing <code>Sort</code> property
with sorting by title. Obviously, we create a new <code>Pageable</code> instance based on the passed one.
If sorting by title was present in the <code>pageable</code> passed in we should copy the <code>Sort.Direction</code>
property of that.

    default List<Book> findAllSortByTitle(Pageable pageable) {

        Sort.Order titleSortOrderIfExist = pageable.getSort().getOrderFor("title");
        Sort.Direction titleSortOrderDirection = titleSortOrderIfExist != null ?
                titleSortOrderIfExist.getDirection() : Sort.DEFAULT_DIRECTION;


        Pageable pageableSortByTitle = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(titleSortOrderDirection, "title")
        );

        return findAll(pageableSortByTitle);
    };