### Lesson 87
## Implement saveNewAuthor

    assertThat(saved.getId()).isNotNull(); //failed

What's happening here is our test is running and completing before
The persistence actually happens
So Hibernate is doing a lazy save to the database,
And here we can see
The saved author comes back
And his id is in fact null.
So the test ran, but no insert ever occurred to the database
or where our test is actually running ahead of Hibernate.
So when we exit the method,
Hibernate hasn't done the persistence,
falls back to the test method. 
Then it terminates before Hibernate completes.
So in a larger app, it probably would eventually persist.
But here is not so,
And the one thing that we can do here is do - after em.persist()
we can call

	em.flush() 

The flush() forces Hibernate to flush the transactions to the database,
but we're going to run into another problem here,
so I do want to run that one more time and show you that problem:

we will ran into

	 jakarta.persistence.TransactionRequiredException:
		no transaction in progress.

There actually is been a transaction in progress by Spring Boot
But here outside the test context probably that would not work.
And how we can fix that is to call

	em.joinTransaction();

before <code>em.persist()</code> -n- <code>em.flush()</code>

And now our test is passing, we can see that we're actually getting
a value back for that id.

The em.joinTransaction() would throw an exception
If a transaction wasn't already in process,
But a transaction is being created by Spring Boot.

A Preferable way would be
To either see if this service was an exception
Or we can say

    em.getTransaction().begin();

instead of <code>em.JoinTransaction()</code>

and after <code>em.flush()</code> place

 	em.getTransaction().commit();

So now we're doing a unit of work,
We're beginning the transaction,
We're saving the author to the database,
We're flushing 
Then we're committing the work.

So this is a complete transaction within a hibernate context.
But be aware of these test works are going to be
Persistent to the database,
so it is important to understand
What's happening within a database context
Because now we're taking away from the capability
Where we're actually using Hibernate's Transaction Manager,
which isn't always in sync with Spring Boot Transaction Management.

So, in future
We'll be using Spring Data JPA,
Which has declarative transaction management using annotations
for that.
So here this is not a typical way that we'd be working with it,
But I do watch and understand
What's happening within the context of the database.

So, to save a new Author with Hibernate,
we're getting that entity manager,
which gives us a database connection,
start the transaction,
save it,
flush it to the database,
then commit it.
So very important sequence of steps here.