package guru.springframework.sdjpa.creditcard.config;

import guru.springframework.sdjpa.creditcard.domain.pan.CreditCardPan;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Created by sergei on 10/05/2025
 */
@TestConfiguration
@EnableJpaRepositories(
        basePackages = "guru.springframework.sdjpa.creditcard.repositories.pan",
        entityManagerFactoryRef = "testPanEntityManagerFactory",
        transactionManagerRef = "testPanTransactionManager"
)
@Profile("test")
@EntityScan(basePackages = "guru.springframework.sdjpa.pan.domain")
public class TestPanDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.h2.datasource")
    public DataSourceProperties testPanDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource testPanDataSource(
            @Qualifier("testPanDataSourceProperties") DataSourceProperties panDataSourceProperties
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testpandb;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean testPanEntityManagerFactory(
            @Qualifier("testPanDataSource") DataSource panDataSource,
            EntityManagerFactoryBuilder emfBuilder
    ) {
        return emfBuilder
                .dataSource(panDataSource)
                .packages(CreditCardPan.class)
                .persistenceUnit("pan")
                .build();

    }

    @Bean
    @Primary
    public PlatformTransactionManager testPanTransactionManager(
            @Qualifier("testPanEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory
    ) {
        EntityManagerFactory testEntityManagerFactoryObject = panEntityManagerFactory.getObject();
        Objects.requireNonNull(testEntityManagerFactoryObject);
        return new JpaTransactionManager(testEntityManagerFactoryObject);
    }


}
