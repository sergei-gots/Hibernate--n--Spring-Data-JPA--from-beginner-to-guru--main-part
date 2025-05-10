## Section XXV
# Multiple Data Sources
### Lesson *
## Spring Tests using H2

### Overview 

This section covers the implementation of testing with multiple data sources, particularly focusing on the use of H2 in-memory databases to ensure test data does not interfere with production data.

When working with multiple data sources in Spring, it's important to ensure that test data does not persist or interfere with the actual databases. The use of H2 in-memory databases allows us to achieve a clean and isolated testing environment while maintaining consistency and safety for production databases.

### Objective

In this configuration, we aim to achieve the following goals:

<li>Ensure that test data does not affect production data by using in-memory databases for tests.
<li>Leverage multiple data sources with Spring's <code>@Test</code> profiles and 
Flyway for consistent database migrations across environments.
<li>Configure and manage multiple data sources and Flyway migrations in a way 
that allows for seamless testing and validation.

### H2 In-Memory Database for Testing

Testing with an H2 in-memory database is essential for ensuring the consistency and isolation of test data. 
This prevents any modifications from affecting the production database, 
even when using the <code>@Transactional</code> context in tests. 
Despite the transactional context, test data can still persist if the databases aren't properly isolated. 
Using H2 ensures that all test data is cleared after the tests are executed.
The tests are executed under the test profile, which is specified in the <code>application.properties</code> file:

    spring.profiles.active=test

This allows us to configure properties specific to the test environment, including the H2 in-memory database 
and Flyway migrations for the testing context.

### Configuration

To configure multiple data sources and Flyway migrations for testing, we use the @TestConfiguration annotations in Spring, which allow for custom configurations specific to the test environment. This avoids the need for @Profile("!test") in the main @Configuration classes, which could lead to unwanted exclusions and potential issues with data source configuration.
Example Test Configuration for Flyway and DataSource

Here’s an example configuration that sets up the test database for Flyway migrations:

    @TestConfiguration
    public class TestCardFlywayConfiguration {

        @Bean(initMethod = "migrate")
        public Flyway testCardFlyway() {
            return Flyway.configure()
                .dataSource("jdbc:h2:mem:testcarddb;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false", "sa", "")
                .locations("classpath:/db/migration/card")
                .load();
        }
    }

In this example:

<li>The <b>jdbc:h2:mem:testcarddb;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false</b> 
URL configures an H2 in-memory database with MySQL mode enabled for compatibility.
<li>The Flyway configuration points to the location where migration scripts are stored
(<code>classpath:/db/migration/card</code>).

By using <code>@TestConfiguration</code> and custom beans, you can configure a clean, 
isolated testing environment for each database without risking any interference 
with the main application's configurations.
Common Issues and Solutions

1. Flyway Migration Checksum Mismatch

If you encounter a <code>Migration checksum mismatch error</code>, 
this can occur when the Flyway migrations in the test database 
differ from the migrations applied to the actual database. 
To resolve this, you can run the Flyway repair command to update the schema history:

    mvn flyway:repair

Alternatively, you can revert the changes in the migrations to match the database state.

2. BeanDefinitionOverrideException

When working with multiple test configurations, make sure that each test-specific configuration 
has unique bean names. 
If two configurations attempt to register the same bean, 
you'll encounter a <code>BeanDefinitionOverrideException</code>. 
To solve this, ensure that bean names are distinct, 
and avoid conflicts by renaming beans or using <code>@Primary</code> 
on one of them to indicate which one should be preferred.

Key Takeaways:

<li>Use H2 in-memory databases for test environments to avoid polluting production data.
<li>Leverage Spring profiles (e.g., test) to configure separate beans for testing.
<li>Ensure Flyway migrations are consistent across environments by handling migrations specifically for tests.
<li>Resolve any conflicts between test and production configurations using proper bean names and Spring annotations.