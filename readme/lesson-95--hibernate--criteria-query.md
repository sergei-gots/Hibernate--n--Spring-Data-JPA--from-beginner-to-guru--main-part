### Lesson 95
## Criteria Query

### 1. Clause
   In SQL, a clause is a structural component of a query. The key parts here:

#### SELECT Clause: 
    SELECT * FROM AUTHOR
– retrieves data.

#### WHERE Clause: 
    WHERE LAST_NAME = :PARAM1 AND FIRST_NAME = :PARAM2 
– filters rows.

### 2. Predicate
   A predicate in SQL is an expression that evaluates to <code>TRUE</code>, <code>FALSE</code>, or <code>UNKNOWN</code>. In this case, the predicates are:

        FIRST_NAME = :PARAM1
        LAST_NAME = :PARAM2
        
        FIRST_NAME = :PARAM1 AND LAST_NAME = :PARAM2 

the last one here is a compound predicate.

Predicates determine which rows are included in the result.

### Relation Between Clause and Predicate

<li> WHERE is a <b>clause</b> that restricts query results.

Inside WHERE, the predicate defines the filtering condition.
Conclusion:

    SELECT * FROM AUTHOR is a clause.
    WHERE FIRST_NAME = :PARAM1 AND LAST_NAME = :PARAM2

 is also a clause, containing a predicate 
        
    (FIRST_NAME = :PARAM1 AND LAST_NAME = :PARAM2).






