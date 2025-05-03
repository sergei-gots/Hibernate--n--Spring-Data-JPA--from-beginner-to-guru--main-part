package guru.springframework.sdjpa.creditcard.listeners;

import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener {

    public PreUpdateListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {

        System.out.println("I'm in 'onPreUpdate'. event.getId()=" + event.getId());
        //super.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity(), event);

        return false;
    }

}
