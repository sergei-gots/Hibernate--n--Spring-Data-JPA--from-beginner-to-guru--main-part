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
public class PanFlywayConfiguration {

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

}
