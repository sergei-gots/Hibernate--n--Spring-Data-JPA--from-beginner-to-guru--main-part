package guru.springframework.sdjpa.creditcard.config;

import com.zaxxer.hikari.HikariDataSource;
import guru.springframework.sdjpa.creditcard.domain.pan.CreditCardPan;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by sergei on 05/05/2025
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "guru.springframework.sdjpa.creditcard.repositories.pan",
        entityManagerFactoryRef = "panEntityManagerFactory",
        transactionManagerRef = "panTransactionManager"
)
public class PanDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource.hikari")
    public DataSource panDataSource(
            @Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties
    ) {
        return panDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(
            @Qualifier("panDataSource") DataSource panDataSource,
            EntityManagerFactoryBuilder emfBuilder
    ) {

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");

        LocalContainerEntityManagerFactoryBean emf = emfBuilder
                .dataSource(panDataSource)
                .packages(CreditCardPan.class)
                .persistenceUnit("pan")
                .build();

        emf.setJpaProperties(jpaProperties);

        return emf;
    }

    @Bean
    @Primary
    public PlatformTransactionManager panTransactionManager(
            @Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory
    ) {
        EntityManagerFactory panEntityManagerFactoryObject = panEntityManagerFactory.getObject();
        Objects.requireNonNull(panEntityManagerFactoryObject);
        return new JpaTransactionManager(panEntityManagerFactoryObject);
    }

}
