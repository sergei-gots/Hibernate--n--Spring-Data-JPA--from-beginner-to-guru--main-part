package guru.springframework.sdjpa.creditcard.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class PreUpdateListener implements PreUpdateEventListener {

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        System.out.println("I'm in 'onPreUpdate'");
        return false;
    }

}
