package guru.springframework.sdjpa.creditcard.config;

import guru.springframework.sdjpa.creditcard.interceptors.EncryptionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by sergei on 28/04/2025
 */
//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

    //@Autowired
    EncryptionInterceptor encryptionInterceptor;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", encryptionInterceptor);
    }
}
