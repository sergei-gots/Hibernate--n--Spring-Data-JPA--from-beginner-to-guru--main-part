package guru.springframework.sdjpa.creditcard.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Created by sergei on 10/05/2025
 */
@TestConfiguration
public class TestCardFlywayConfiguration {

    @Bean(name = "testCardFlyway", initMethod = "migrate")
    public Flyway testCardFlyway(@Qualifier("testCardDataSource") DataSource testCardDataSource) {
        return Flyway.configure()
                .dataSource(testCardDataSource)
                .locations("classpath:/db/migration/card")
                .load();
    }

}
