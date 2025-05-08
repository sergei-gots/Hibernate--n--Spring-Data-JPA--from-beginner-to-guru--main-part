package guru.springframework.sdjpa.creditcard.config;

import com.zaxxer.hikari.HikariDataSource;
import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Created by sergei on 05/05/2025
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "guru.springframework.sdjpa.creditcard.repositories.creditcard",
        entityManagerFactoryRef = "cardEntityManagerFactory",
        transactionManagerRef = "cardTransactionManager"
)
public class CardDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.card.datasource")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.card.datasource.hikari")
    public DataSource cardDataSource(
            @Qualifier("cardDataSourceProperties") DataSourceProperties cardDataSourceProperties
    ) {
        return cardDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(
            @Qualifier("cardDataSource") DataSource cardDataSource,
            EntityManagerFactoryBuilder emfBuilder
    ) {
        return emfBuilder
                .dataSource(cardDataSource)
                .packages(CreditCard.class)
                .persistenceUnit("card")
                .build();
    }

    @Bean
    public PlatformTransactionManager cardTransactionManager(
            @Qualifier("cardEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardEntityManagerFactory
    ) {
        EntityManagerFactory cardEntityManagerFactoryObject = cardEntityManagerFactory.getObject();
        Objects.requireNonNull(cardEntityManagerFactoryObject);
        return new JpaTransactionManager(cardEntityManagerFactoryObject);
    }
}
