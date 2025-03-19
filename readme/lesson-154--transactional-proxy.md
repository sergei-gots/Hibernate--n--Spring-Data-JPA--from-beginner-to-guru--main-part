#### Section 18. 
### Lesson 154
##  @Transactional Proxy clarification

https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html

#### Lazy initialization failure cause 

If you move <code>@Transactional</code> to <code>readOrderDataById(Long id)</code>, 
and <code>run(...)</code> is no longer transactional, then the transaction will only work inside the 
<code>readOrderDataById(id)</code> method.

But because it is called from inside the class via 
<code>this.readOrderDataById(id)</code>,
Spring does not apply the AOP proxy, and the transaction is NOT opened.
What happens in this case?

The <code>run(...)</code> method is called without a transaction.
<code>run(...)</code> calls <code>this.readOrderDataById(id)</code>.
<code>@Transactional</code> does not work, because it is called directly (without the Spring proxy).
<code>readOrderDataById(id)</code> executes <code>orderDataRepository.findById(id)</code>, 
but without an active transaction.
Hibernate executes the query, closes the Session immediately after executing the query.
When you then try to get <code>orderData.getOrderLines()</code>, 
Hibernate can't load the LAZY relationships because Session is already closed.
You get a <code>LazyInitializationException</code>.


#### How to fix that?
Variant 1: To call the method through Spring-context (best practice here):

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        orderDataReadService.readOrderDataById(12122L); // Теперь @Transactional сработает!
    }

So Spring will create a Proxy and open a Transaction.

Variant 2: Keep  @Transactional upon the <code>run(...)</code> method.




































