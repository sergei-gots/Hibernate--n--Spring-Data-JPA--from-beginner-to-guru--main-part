package guru.springframework.sdjpa.creditcard.config;

import guru.springframework.sdjpa.creditcard.domain.cardholder.CreditCardHolder;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
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
@Profile("test")
@EnableJpaRepositories(
        basePackages = "guru.springframework.sdjpa.creditcard.repositories.cardholder",
        entityManagerFactoryRef = "testCardHolderEntityManagerFactory",
        transactionManagerRef = "testCardHolderTransactionManager"
)
@EntityScan(basePackages = "guru.springframework.sdjpa.cardholder.domain")
public class TestCardHolderDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.h2.datasource")
    public DataSourceProperties testCardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource testCardHolderDataSource(
            @Qualifier("testCardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testcardholderdb;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean testCardHolderEntityManagerFactory(
            @Qualifier("testCardHolderDataSource") DataSource cardHolderDataSource,
            EntityManagerFactoryBuilder emfBuilder
    ) {
        return emfBuilder
                .dataSource(cardHolderDataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardholder")
                .build();

    }

    @Bean
    public PlatformTransactionManager testCardHolderTransactionManager(
            @Qualifier("testCardHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory
    ) {
        EntityManagerFactory panEntityManagerFactoryObject = panEntityManagerFactory.getObject();
        Objects.requireNonNull(panEntityManagerFactoryObject);
        return new JpaTransactionManager(panEntityManagerFactoryObject);
    }


}
