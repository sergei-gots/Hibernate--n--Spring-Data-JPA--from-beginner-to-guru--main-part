## Section XXV
# Multiple Data Sources
### Lessons 209 -n- 210
## Get Data Source @ConfigurationProperties and Data Source instances 

Our application.properties will include three sets of properties like e.g. that:

    spring.pan.datasource.username=panuser
    spring.pan.datasource.password=password
    spring.pan.datasource.url=jdbc:mysql://127.0.0.1:3306/pan?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    spring.pan.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


Correspondingly, we will have three <code>@Configuration</code> classes
returning <code>@ConfigurationProperties</code>. One of the method returning
<code>DataSourceProperties</code> in one of the classes must be marked
as <code>@Primary</code>:

    @Configuration
    public class PanDatabaseConfigruation {
    
        @Bean
        @Primary
        @ConfigurationProperties("spring.pan.datasource")
        public DataSourceProperties panDataSourceProperties() {
            return new DataSourceProperties();
        }
    }

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