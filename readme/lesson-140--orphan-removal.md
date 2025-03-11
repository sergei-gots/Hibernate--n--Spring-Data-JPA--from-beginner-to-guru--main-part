#### Section 15. Database Relationship Mappings
### Lesson 140
##  orphanRemoval = true

Let's consider the difference between

    @OneToOne(cascade = {CascadeType.PERSIST), orphanRemoval = true )
    private OrderApproval orderApproval;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
    private OrderApproval orderApproval;

Let's break down the two cases in more detail:
1. <b>@OneToOne(cascade = {CascadeType.PERSIST}, orphanRemoval = true)</b>

        cascade = CascadeType.PERSIST: 
This specifies that when the parent entity (the one with the @OneToOne annotation) is persisted (saved), the associated OrderApproval entity will also be persisted. In other words, if you save the parent entity, it will automatically persist the OrderApproval entity as well.
orphanRemoval = true: This means that if the OrderApproval entity is removed from the parent entity's relationship (for example, removed from the field holding the OrderApproval reference), the OrderApproval entity will be deleted from the database. The entity becomes an "orphan" and will be removed when it is no longer referenced by the parent.

When it happens:

<li>Persisting: When you persist (save) the parent entity, the associated OrderApproval will also be persisted.
<li>Removing: If you set the orderApproval field of the parent entity to null (i.e., removing the reference to the OrderApproval), it will be removed from the database.
    Example:

    Customer customer = new Customer();
    OrderApproval approval = new OrderApproval();
    customer.setOrderApproval(approval);
    customerRepository.save(customer);  // This will persist both customer and approval

    // Later, if you set orderApproval to null:
    customer.setOrderApproval(null);  // The approval entity will be deleted from the database.

2. <b>@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE })</b>

   cascade = CascadeType.PERSIST: Similar to the first case, this ensures that when the parent entity is saved, the associated OrderApproval entity is also persisted.
   cascade = CascadeType.REMOVE: This means that if the parent entity is deleted, the associated OrderApproval entity will be deleted as well. In other words, when you remove the parent entity, the OrderApproval entity will also be deleted from the database automatically.
   orphanRemoval is not specified in this case, meaning that the OrderApproval entity will not be deleted when it's removed from the relationship (when the reference is set to null or the entity is removed from the relationship collection).

When it happens:

    Persisting: The behavior is the same as in the first case — persisting the parent will also persist the OrderApproval.
    Removing: When the parent entity is deleted, the OrderApproval entity will also be deleted.
    Example:

    Customer customer = new Customer();
    OrderApproval approval = new OrderApproval();
    customer.setOrderApproval(approval);
    customerRepository.save(customer);  // This will persist both customer and approval

    // Later, if you delete the customer:
    customerRepository.delete(customer);  // This will also delete the associated approval from the database.

#### Key Differences:
Feature	cascade = {CascadeType.PERSIST}, orphanRemoval = true	cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
Persisting	Both the parent and the OrderApproval are persisted when the parent is saved.	Both the parent and the OrderApproval are persisted when the parent is saved.
Deleting	The OrderApproval is deleted from the database when removed from the relationship (null assignment or removing the reference).	The OrderApproval is deleted only when the parent is deleted (not when removed from the relationship).
Use case	Use this when you want to remove and delete the child entity (OrderApproval) when it is removed from the relationship (set to null).	Use this when you want to delete the child entity (OrderApproval) only when the parent entity is deleted, but not when the child is just removed from the relationship.

#### Summary:

orphanRemoval = true automatically deletes the child entity when it's removed from the parent entity's relationship (even without the parent being deleted).
CascadeType.REMOVE ensures that the child entity is deleted when the parent entity is deleted, but not when the child is removed from the relationship (i.e., setting the reference to null).