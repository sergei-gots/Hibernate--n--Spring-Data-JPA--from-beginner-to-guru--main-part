package guru.springframework.sdjpa.creditcard.listeners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class LoadListener implements LoadEventListener {

    @Override
    public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
        System.out.println("I'm in 'onLoad'. event.getResult=" + event.getResult() + ", loadType=" + loadType.toString());
    }

}
