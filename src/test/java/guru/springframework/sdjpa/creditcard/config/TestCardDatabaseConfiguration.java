package guru.springframework.sdjpa.creditcard.config;

import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
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
        basePackages = "guru.springframework.sdjpa.creditcard.repositories.creditcard",
        entityManagerFactoryRef = "testCardEntityManagerFactory",
        transactionManagerRef = "testCardTransactionManager"
)
@EntityScan(basePackages = "guru.springframework.sdjpa.creditcard.domain")
public class TestCardDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.h2.datasource")
    public DataSourceProperties testCardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.card.datasource.hikari")
    public DataSource testCardDataSource(
            @Qualifier("testCardDataSourceProperties") DataSourceProperties cardDataSourceProperties
    ) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testcarddb;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean testCardEntityManagerFactory(
            @Qualifier("testCardDataSource") DataSource cardDataSource,
            EntityManagerFactoryBuilder emfBuilder
    ) {

        return  emfBuilder
                .dataSource(cardDataSource)
                .packages(CreditCard.class)
                .persistenceUnit("card")
                .build();
    }

    @Bean
    public PlatformTransactionManager testCardTransactionManager(
            @Qualifier("testCardEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardEntityManagerFactory
    ) {
        EntityManagerFactory cardEntityManagerFactoryObject = cardEntityManagerFactory.getObject();
        Objects.requireNonNull(cardEntityManagerFactoryObject);
        return new JpaTransactionManager(cardEntityManagerFactoryObject);
    }


}
