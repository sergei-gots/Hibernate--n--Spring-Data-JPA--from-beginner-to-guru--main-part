## Section XXV
# Multiple Data Sources
### Lessons 219
## Configuring Hibernate Validation Properties


We will add property <b>hibernate.hbm2ddl.auto"</b> with the value <b>validate</b> 
and set that to <b>EntityManagerFactory</b> in our database <b>@Configuration</b> classes
in the method that builds that <b>EntityManagerFactory</b>:

    Properties jpaProperties = new Properties();
    props.put("hibernat.hbm2ddl.auto", "validate");
    entityManagerFactory.setJpaProperties(jpaProperties);

<b>hbm2ddl</b> means <b>Hibernate Mapping To create schema DDL</b>.    


    