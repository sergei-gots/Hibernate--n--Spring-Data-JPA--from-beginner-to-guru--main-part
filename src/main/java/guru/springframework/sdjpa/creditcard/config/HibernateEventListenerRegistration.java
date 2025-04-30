package guru.springframework.sdjpa.creditcard.config;

import guru.springframework.sdjpa.creditcard.listeners.LoadListener;
import guru.springframework.sdjpa.creditcard.listeners.PreInsertListener;
import guru.springframework.sdjpa.creditcard.listeners.PreUpdateListener;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 30/04/2025
 */
@Component
public class HibernateEventListenerRegistration implements BeanPostProcessor {

    @Autowired
    PreInsertListener preInsertListener;

    @Autowired
    PreUpdateListener preUpdateListener;

    @Autowired
    LoadListener loadListener;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {

            EntityManagerFactory entityManagerFactory = localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory();

            if (entityManagerFactory instanceof SessionFactoryImpl sessionFactory) {
                ServiceRegistryImplementor serviceRegistry = sessionFactory.getServiceRegistry();

                EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

                assert eventListenerRegistry != null;
                eventListenerRegistry.appendListeners(EventType.PRE_INSERT, preInsertListener);
                eventListenerRegistry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
                eventListenerRegistry.appendListeners(EventType.LOAD, loadListener);
                System.out.println(this.getClass().getSimpleName() + ": PRE_INSERT-, PRE_UPDATE-, LOAD-listeners are successfully registered");
            }
            else {
                throw new IllegalStateException("can't get a SessionFactoryImpl to register Hibernate Event Listeneres");
            }
        }

        return bean;
    }
}
