### Section 15. 
### Lesson 128. created_date 
@org.hibernate.annotations 
## @CreationTimeStamp

SQL:

    ALTER TABLE order_header ADD COLUMN created_date TIMESTAMP 

or 

    ALTER TABLE order_header ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP


Java:

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;





