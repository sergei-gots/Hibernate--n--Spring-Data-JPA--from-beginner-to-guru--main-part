package guru.springframework.sdjpa.creditcard.listeners;

import guru.springframework.sdjpa.creditcard.annotations.EncryptedString;
import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import org.hibernate.event.spi.AbstractPreDatabaseOperationEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by sergei on 01/05/2025
 */
public class AbstractEncryptionListener {

    private final EncryptionService encryptionService;

    public AbstractEncryptionListener(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public void encrypt(Object[] state, String[] propertyNames, Object entity, AbstractPreDatabaseOperationEvent event) {

        ReflectionUtils.doWithFields(
                entity.getClass(),
                field -> encryptField(field, state, propertyNames, entity, event),
                this::isFieldToBeEncrypted
        );
    }

    public void decrypt(Object entity) {

        ReflectionUtils.doWithFields(
                entity.getClass(),
                field -> decryptField(field, entity),
                this::isFieldToBeEncrypted
        );
    }

    private boolean isFieldToBeEncrypted(Field field) {
        return AnnotationUtils.findAnnotation(field, EncryptedString.class) != null;
    }

    private void encryptField(Field field, Object[] state, String[] propertyNames, Object entity, AbstractPreDatabaseOperationEvent preDatabaseOperationEvent) {

        int idx = getPropertyIndex(field.getName(), propertyNames);
        Object currentValue = state[idx];
        System.out.println("currentValue = " + currentValue);
        state[idx] = encryptionService.encrypt(currentValue.toString());
        System.out.println("state[idx] = " + state[idx].toString());
    }

    private void decryptField(Field field, Object entity) {

        field.setAccessible(true);
        Object value = ReflectionUtils.getField(field, entity);
        assert value != null;
        System.out.println("value to decrypt = " + value);
        ReflectionUtils.setField(field, entity, encryptionService.decrypt(value.toString()));
    }

    private int getPropertyIndex(String name, String[] propertyNames) {

        for (int i = 0 ; i < propertyNames.length; i++) {
            if (propertyNames[i].equals(name)) {
                return i;
            }
        }

        throw new IllegalStateException("Property named '" + name + "' is not found");
    }



}
