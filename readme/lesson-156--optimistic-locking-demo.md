#### Section 18.
### Lesson 156
##  JPA @Version entity's property. Optimistic Locking Demo

If we don't actualize <code>savedCustomer</code> after the current merge, i.e. 
perform <code>customerRepository.save()</code> without update our
<code>savedCustomer</code> with

    savedCustomer = customerRepository(savedCustomer);

so if our code includes only

    customerRepository.save(savedCustomer);

then next <code>save(savedCustomer)</code> will cause throwing

    org.hibernate.StaleObjectStateException

and then

    org.springframework.ObjectOptimisticLockingFailureException

with the message 

     Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)

Basically it's saying that the expected version did not match the provided version.
After update <code>savedCustomer</code> with a new name the method
<code>save</code> will return a new object with the proper version, and the existing
one <code>savedCustomer</code> will become invalid. And correspondingly we should update
our object we operate on:

    savedCustomer = customerRepository.save(savedCustomer);

or, to be verbatim:

    Customer updatedCustomerVersion2 = customerRepository.saved(savedCustomer);

Also, we can see, the <code>customerName</code> for different objects also differs.

#### Hibernate statements due demo are

First, we save a new customer:

    Hibernate: insert into customer (address,city,state,zip_code,created_date,customer_name,email,last_modified_date,phone,version) values (?,?,?,?,?,?,?,?,?,?)

Then 3 times we will have the merge: 

    Hibernate: select c1_0.id,c1_0.address,c1_0.city,c1_0.state,c1_0.zip_code,c1_0.created_date,c1_0.customer_name,c1_0.email,c1_0.last_modified_date,c1_0.phone,c1_0.version from customer c1_0 where c1_0.id=?
    Hibernate: update customer set address=?,city=?,state=?,zip_code=?,customer_name=?,email=?,last_modified_date=?,phone=?,version=? 
        where id=? and version=?

and finally we delete the customer:

    Hibernate: select c1_0.id,c1_0.address,c1_0.city,c1_0.state,c1_0.zip_code,c1_0.created_date,c1_0.customer_name,c1_0.email,c1_0.last_modified_date,c1_0.phone,c1_0.version 
        from customer c1_0 where c1_0.id=?
    Hibernate: delete from customer where id=? 
        and version=?

So, we have, in total, 5 consequently executed transactions here.


#### Use case
What is about this org.hibernate.<code>StaleObjectStateException</code>?
It's going to bubble that up and say, Hey, you've got a stale object.
It is very important toolset that we have with Hibernate as far as detecting stale data.
Ideal use case for this would be like a web form where you submitted that and maybe something else come
back updates it.
<br>But the end user on the web, they don't know.
So they submit the changes.
And now that underlying data is changed, and you can detect 
that this long-lived conversational transaction  by using this technique is exactly here.
