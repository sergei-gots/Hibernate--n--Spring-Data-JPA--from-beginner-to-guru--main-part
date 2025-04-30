package guru.springframework.sdjpa.creditcard.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by sergei on 28/04/2025
 */
@Configuration
public class HibernatePropertiesAppender implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        System.out.println("I'm on 'HibernatePropertiesAppender.customize;");
        //If there are some difficulties with LoadEventListener.onLoad, one can apply the next property:
        //hibernateProperties.put("hibernate.event.allow_load_callbacks", true);
    }
}
