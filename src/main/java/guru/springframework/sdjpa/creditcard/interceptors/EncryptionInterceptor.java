package guru.springframework.sdjpa.creditcard.interceptors;

import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import org.hibernate.type.Type;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sergei on 28/04/2025
 */
@Component
public class EncryptionInterceptor implements Interceptor, Serializable {

    @Autowired
    EncryptionService encryptionService;

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {

        System.out.println("I'm in onLoad");

        return encryptAnnotatedFields(entity, state, propertyNames, "onLoad");
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
            throws CallbackException {

        System.out.println("I'm in onFlushDirty");

        return encryptAnnotatedFields(entity, currentState, propertyNames, "onFlushDirty");

    }


    @Override
    public boolean onPersist(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {

        System.out.println("I'm in onPersist");


        return encryptAnnotatedFields(entity, state, propertyNames, "onPersist");
    }

    /**
     * @return true if the state was altered, otherwise - false
     */
    private boolean encryptAnnotatedFields(Object entity, Object[] state, String[] propertyNames, String type) {

        Set<String> annotatedFields = getEncryptedStringAnnotatedFields(entity);

        if (annotatedFields.isEmpty()) {
            return false;
        }

        if (! ("onLoad".equals(type) || "onPersist".equals(type) || "onFlushDirty".equals(type)) ) {
            return false;
        }

        for (int i = 0; i < state.length; i++) {
            if (annotatedFields.contains(propertyNames[i])) {
                state[i] = "onLoad".equals(type) ?
                        encryptionService.decrypt(state[i].toString()) :
                        encryptionService.encrypt(state[i].toString());
            }
        }

        return true;

    }


    Set<String> getEncryptedStringAnnotatedFields(Object entity) {

        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(EncryptedString.class))
                .map(Field::getName)
                .collect(Collectors.toSet());
    }
}
