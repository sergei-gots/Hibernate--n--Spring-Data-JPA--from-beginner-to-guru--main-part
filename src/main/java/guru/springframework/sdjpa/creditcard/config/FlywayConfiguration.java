package guru.springframework.sdjpa.creditcard.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sergei on 07/05/2025
 */
@Configuration
public class FlywayConfiguration {

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    DataSourceProperties panFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway panFlyway(
            @Qualifier("panFlywayDataSourceProperties") DataSourceProperties panFlywayDataSourceProperties
    ) {
        return Flyway.configure()
                .dataSource(
                        panFlywayDataSourceProperties.getUrl(),
                        panFlywayDataSourceProperties.getUsername(),
                        panFlywayDataSourceProperties.getPassword()
                )
                .locations("classpath:/db/migration/pan")
                .load();
    }

    @Bean
    @ConfigurationProperties("spring.card.flyway")
    DataSourceProperties cardFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardFlyway(
            @Qualifier("cardFlywayDataSourceProperties") DataSourceProperties cardFlywayDataSourceProperties
    ) {
        return Flyway.configure()
                .dataSource(
                        cardFlywayDataSourceProperties.getUrl(),
                        cardFlywayDataSourceProperties.getUsername(),
                        cardFlywayDataSourceProperties.getPassword()
                )
                .locations("classpath:/db/migration/card")
                .load();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    DataSourceProperties cardHolderFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway cardHolderFlyway(
            @Qualifier("cardHolderFlywayDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties
    ) {
        return Flyway.configure()
                .dataSource(
                        cardHolderDataSourceProperties.getUrl(),
                        cardHolderDataSourceProperties.getUsername(),
                        cardHolderDataSourceProperties.getPassword()
                )
                .locations("classpath:/db/migration/cardholder")
                .load();
    }
}
