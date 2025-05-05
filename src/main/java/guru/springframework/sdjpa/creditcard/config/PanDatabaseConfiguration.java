package guru.springframework.sdjpa.creditcard.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by sergei on 05/05/2025
 */
@Configuration
public class PanDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }
}
