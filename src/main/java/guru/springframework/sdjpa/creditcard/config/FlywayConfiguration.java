package guru.springframework.sdjpa.creditcard.config;

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

    @Bean
    @ConfigurationProperties("spring.card.flyway")
    DataSourceProperties cardFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    DataSourceProperties cardHolderFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }
}
