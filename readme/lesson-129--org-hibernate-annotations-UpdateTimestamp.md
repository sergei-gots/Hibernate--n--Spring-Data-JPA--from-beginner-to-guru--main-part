### Section 15. 
### Lesson 129. Hibernate Update Date 
@org.hibernate.annotations 
## @UpdateTimestamp

SQL:

    ALTER TABLE order_header ADD COLUMN last_modified_date TIMESTAMP;

Java:

    @UpdateTimestamp
    private Timestamp lastModifiedDatea;

Tests:

<li>testSave
    
    assertNotNull(saved.getLastModifiedDate());
    assertThat(saved.getCreatedDate()).isNotEqualTo(saved.getLastModifiedDate());;


<li>testUpdate

    assertNotNull(updated.getLastModifiedDate());

    long timeDiffMillis = saved.getLastModifiedDate().getTime() - saved.getCreatedDate().getTime();
    
    assertThat(timeDiffMillis).isLessThan(10);





