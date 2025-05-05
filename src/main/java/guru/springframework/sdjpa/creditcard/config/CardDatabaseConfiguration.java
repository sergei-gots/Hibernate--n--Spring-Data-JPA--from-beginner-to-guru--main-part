package guru.springframework.sdjpa.creditcard.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sergei on 05/05/2025
 */
@Configuration
public class CardDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.card")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }
}
