package guru.springframework.beer.exceptions;

import java.util.UUID;

/**
 * Created by sergei on 24/05/2025
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?>  entityClass, UUID entityId) {
        this(entityClass, "id", entityId.toString());
    }

    public NotFoundException(Class<?>  entityClass, String searchField, String searchFieldValue) {
        super (entityClass.getSimpleName() + " with " + searchField + '=' + searchFieldValue + " not found in the database");
    }
}
