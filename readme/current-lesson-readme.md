#### Chapter 14: Paging-n-Sorting
## Lesson 120 Paging-n-Sorting with Spring Data Jpa


For clarity, we will refactor the class <code>BookDaoImpl</code> with renaming to <code>BookDaoSpringDataJpaImpl</code>
<br>
Then we will copy-paste <code>BookDaoHibernateTest</code> creating the <code>BookDaoSpringDataJpaImplTest</code>,
and make some fixes to apply this test to the <code>BookDaoSpringDataJpaImpl<code>.

All what we will need to do in the <code>BookDaoSpringDataJpaImpl</code> is to replace
stub with actual code for the <code>findAll(int limit, int offset)</code> method:

    @Override
    public List<Book> findAll(int limit, int offset) {
        return findAll(PageRequest.of(offset/limit, limit));
    }