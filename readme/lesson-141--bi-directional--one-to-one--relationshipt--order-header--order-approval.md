#### Section 15. Database Relationship Mappings
### Lesson 141
##  bi-directional @OneToOne

#### What we want to do:

We want to have with setting <code>orderHeader.orderApproval = null;</code>
the corresponding <code>order_approval</code> row deleted from the database.
In this case we will continue use <code>orphanRemoval = true</code>.
The annotation will have the next look:

    @OneToOne(cascade = {CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "orderHeader")
    private OrderApproval orderApproval;

#### What do we have here:

<li>Remove <code>CascadeType.REMOVE</code>
        CascadeType.REMOVE removes child entity (OrderApproval) only with removing the corresponding OrderHeader.
    But if we just remove the reference (with setting <code>orderApproval = null;</code>), it won't work.
<li>Add <code>orphanRemoval = true</code>
        This will point out Hibernate to remove automatically OrderApproval from the database,
if it is not referred more from the database and it is not referencing more to any OrderHeader entity 
(i.e. <code>orderApproval.orderHeader == null </code>.

#### How it will work:

    OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();  
    orderHeader.setOrderApproval(null);  // tier the reference to an order approval
    orderHeaderRepository.save(orderHeader);  // Now Hibernate will remove OrderApproval from the database

#### Why it is better?

<li><b>Safety</b>: CascadeType.REMOVE removes OrderApproval only with deletiing of OrderHeader.
<li><b>It's smarter</b>: orphanRemoval = true removes OrderApproval, if it is simply not referring more (became null).
<li><b>Simplicity</b>: Since now removing unused OrderApproval-s from the database will not require additional code.



