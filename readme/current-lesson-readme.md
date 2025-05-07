## Section XXV
# Multiple Data Sources
### Lessons 209 - 213
## Configuring Multiple Data Sources with @Configuration class

### ✅ 0. application.properties 

<code>application.properites</code> will include three sets of properties like e.g. that:

    spring.pan.datasource.username=panuser
    spring.pan.datasource.password=password
    spring.pan.datasource.url=jdbc:mysql://127.0.0.1:3306/pan?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    spring.pan.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


### ✅ 1. @ConfigurationProperties

We will have three <code>@Configuration</code> classes
that have methods which return <code>@ConfigurationProperties</code>. 
One of the method returning <code>DataSourceProperties</code> in one of the classes must be marked
as <code>@Primary</code>. Annotation <code>@Primary</code> will be set upon all the methods of one
of the <code>@Configuration</code> classes. 
It will prevent throwing <code>NonUniqueBeanDefinitionException</code>
See https://www.baeldung.com/spring-primary for details.

    @Configuration
    public class PanDatabaseConfigruation {
    
        @Bean
        @Primary
        @ConfigurationProperties("spring.pan.datasource")
        public DataSourceProperties panDataSourceProperties() {
            return new DataSourceProperties();
        }
    }

### ✅ 2. Building DataSources

The next step we will take is to add methods to the @Configuration classes that return <code>DataSource</code>.
These method will get as an argument a qualified with <code>@Qualifier("<method-name>")</code> <code>DataSourceProperties</code> :

    @Bean
    @Primary //one of these must be primary
    pulbic DataSource panDataSource(
        @Qualifier("panDatSourceProperties") DataSourceProperties panDataSourceProperties 
    ) {
        return panDataSourceProperties
            .initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }

### ✅ 3. Building EntityManagerFactories

Then we will create methods that build up <code>EntityManagerFactoryBean</code>s needed 
to create <b>Entity Manager</b> to work with DataSources we got: 

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(
        @Qualifier("panDataSource") DataSource panDataSource,
        org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder emfBuilder
    ) {
        return emfBuilder
            .dataSource(panDataSource)
            .packages(CreditCardPan.class)
            .persistenceUnit("pan")
            .build();
    }

### ✅ 4. Creating TransactionManagers

    @Bean
    @Primary
    public PlatformTransactionManager panTransactionManager(
        @Qualifier("panEntityManagerFactory") LoadContainerEntityManagerFactoryBean panEntityManagerFactory
    ) {
        EntityManagerFactory panEntityManagerFactoryObject = panEntityManagerFactory.getObject();
        Objects.requireNotNull(panEntityManagerFactoryObject);
        return new JpaTransactionManager(panEntityManagerFactoryObject);
    }

### ✅ 5 @EnableJpaRepositories

We need also to add to the configuration class declaration the annotation <code>@EnableJpaRepositories</code>
in order to specify which JpaRepositories will be used together with specified in the configuration class
<b>entityManagerFactory</b> and <b>transactionManager</b>:

    @EnableJpaRepositories(
        basePackages = "guru.springframework.sdjpa.creditcard.repositories.pan",
        entityManagerFactoryRef = "panEntityManagerFactory",
        transactionManagerRef = "panTransactionManager"
    )

    