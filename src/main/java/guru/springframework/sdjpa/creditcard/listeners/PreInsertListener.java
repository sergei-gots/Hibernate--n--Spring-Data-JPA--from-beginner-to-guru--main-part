package guru.springframework.sdjpa.creditcard.listeners;

import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {

    public PreInsertListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {

        System.out.println("I'm in 'onPreInsert'. event.getId()=" + event.getId());
        super.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity(), event);

        return false;
    }
}
