#### Chapter 14: Paging-n-Sorting
###  Lesson 119. Paging-n-Sorting with Hibernate. HQL queries

In this commit we'll complete implementation for the method 

        Book findAll(Pageable pageable);

in the class <code>BookDaoHibernateImpl</code>

Also, we'll make <code>findAll(Pageable pageable)</code> more universal,
and in the implementation we will discover all the sorting properties and their values
to build the sorting clause based on the content of the <code>pageable.getSort()</code>
passed in the method:

        ...

        StringBuilder hql = new StringBuilder("FROM book ");

        StringBuilder orderClause = new StringBuilder();
        pageable.getSort().get()
                .forEach((order) -> {
                    orderClause.append(orderClause.isEmpty() ? " ORDER BY " : ", ");
                    orderClause.append(order.getProperty());
                    orderClause.append(' ');
                    orderClause.append(order.getDirection());
                });

        hql.append(orderClause);
        hql.append(" LIMIT :pageSize OFFSET :offset");

        ...

The query's parameters <code>LIMIT</code> and <code>OFFSET</code> will be set through the Hibernate provided methods:

    query.setMaxResults(pageable.getPageSize());
    query.setFirstResult((int) offset);

Offset here is being passed not directly from <code>pageable</code>, but through retrieval and check:

        query.setFirstResult(Math.toIntExact(pageable.getOffset()));

It is because the <code>pageable.getOffset()</code> returns <code>long</code> but
<code>TypedQuery.setFirstResult(int startPosition)</code> accepts <code>int</code>.
<br><br>
<u>Note</u>: we are not allowed to pass <code>LIMIT</code> and <code>OFFSET</code> as named parameters in <code>HQL</code>.
