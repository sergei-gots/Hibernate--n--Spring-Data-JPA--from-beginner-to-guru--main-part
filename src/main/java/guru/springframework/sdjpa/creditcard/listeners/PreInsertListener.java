package guru.springframework.sdjpa.creditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class PreInsertListener implements PreInsertEventListener {

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        System.out.println("I'm in 'onPreInsert'");
        return false;
    }
}
