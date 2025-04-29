package guru.springframework.sdjpa.creditcard.listeners;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 29/04/2025
 */
@Component
public class PostLoadListener implements PostLoadEventListener {

    @Override
    public void onPostLoad(PostLoadEvent event) {
        System.out.println("I'm in 'onPostLoad'");
    }

}
