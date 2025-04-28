package guru.springframework.sdjpa.creditcard.interceptors;

import org.hibernate.Interceptor;

import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 28/04/2025
 */
@Component
public class EncryptionInterceptor implements Interceptor {

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) {

        System.out.println("I'm in onLoad");

        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

        System.out.println("I'm in onFlushDirty");

        return false;
    }

    @Override
    public boolean onPersist(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) {

        System.out.println("I'm in onSave");

        return false;
    }

}
