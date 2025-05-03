package guru.springframework.sdjpa.creditcard.listeners;

import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class LoadListener extends AbstractEncryptionListener implements LoadEventListener {

    public LoadListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
        System.out.println("I'm in 'onLoad'. event.getResult=" + event.getResult() + ", loadType=" + loadType.toString());
        super.decrypt(event.getResult());
    }

}
