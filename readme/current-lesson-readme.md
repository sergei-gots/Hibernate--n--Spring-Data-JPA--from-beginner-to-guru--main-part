#### Section 18.
### Lesson 155
##  JPA @Version entity's property. Optimistic Locking implementation

SQL:

    ALTER TABLE customer ADD COLUMN version INTEGER;

Java:

    class Customer extends BaseEntity {
        ...
        @Version
        private Integer version;
        ...
    }