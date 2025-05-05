package guru.springframework.sdjpa.creditcard.config;

import com.zaxxer.hikari.HikariDataSource;
import guru.springframework.sdjpa.creditcard.domain.cardholder.CreditCardHolder;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Created by sergei on 05/05/2025
 */
@Configuration
public class CardHolderDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cardHolderDataSource(
            @Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties
    ) {
        return cardHolderDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(
            @Qualifier("cardHolderDataSource") DataSource cardHolderDataSource,
            EntityManagerFactoryBuilder emfBuilder
    ) {
        return emfBuilder
                .dataSource(cardHolderDataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardholder")
                .build();
    }

    @Bean
    public PlatformTransactionManager cardHolderTransactionManager(
            @Qualifier("cardHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory
    ) {
        EntityManagerFactory cardHolderEntityManagerFactoryObject = cardHolderEntityManagerFactory.getObject();
        Objects.requireNonNull(cardHolderEntityManagerFactoryObject);
        return new JpaTransactionManager(cardHolderEntityManagerFactoryObject);
    }
}
