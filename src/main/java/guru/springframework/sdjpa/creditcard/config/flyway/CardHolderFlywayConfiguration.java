package guru.springframework.sdjpa.creditcard.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by sergei on 07/05/2025
 */
@Profile("!test")
@Configuration
public class CardHolderFlywayConfiguration {

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
