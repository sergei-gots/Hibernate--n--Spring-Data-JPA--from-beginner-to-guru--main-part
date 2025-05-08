## Section XXV
# Multiple Data Sources
### Lessons 218
## Tweaking Hikari CP properties

### ✅ 0. application.properties

We place <b>Hikari Connection Pool</b> properties under <code>spring.[<db-name.>].datasource.hikari.</code>.
E.g. we can specify Hikari Pool Name:

    spring.pan.datasource.hikari.poolname=HikariPool-Pan

### ✅ 1. Reading properties in @Configuration class

    @Configuration  
    @EnableJpaRepositories(...)
    public PanDatabaseConfiguration {
    ...
    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource.hikari")
    DataSource panDataSource(
        @Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties
    ) {
       return panDataSourceProperties
            .initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }
    ...
    } 


### Output

We will have the next output due startup/shutdown:

2025-05-08T12:07:30.158+03:00  INFO 7914 --- [spring-data-jpa-credit-card-project] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-Pan - Starting...
2025-05-08T12:07:30.171+03:00  INFO 7914 --- [spring-data-jpa-credit-card-project] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-Pan - Added connection com.mysql.cj.jdbc.ConnectionImpl@2823b7c5
2025-05-08T12:07:30.171+03:00  INFO 7914 --- [spring-data-jpa-credit-card-project] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-Pan - Start completed.

2025-05-08T12:18:09.688+03:00  INFO 7914 --- [spring-data-jpa-credit-card-project] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-Pan - Shutdown initiated...
2025-05-08T12:18:09.692+03:00  INFO 7914 --- [spring-data-jpa-credit-card-project] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-Pan - Shutdown completed.
