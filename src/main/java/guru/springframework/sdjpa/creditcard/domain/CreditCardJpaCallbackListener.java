package guru.springframework.sdjpa.creditcard.domain;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sergei on 03/05/2025
 */
public class CreditCardJpaCallbackListener {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardJpaCallbackListener.class);

    @PrePersist
    @PreUpdate
    void onPrePersist(CreditCard creditCard) {
        logger.info("on @PrePersist/@PreUpdate. cc id={}", creditCard.getId());
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    void onPostLoad(CreditCard creditCard) {
        logger.info("on @PostPersist/@PostUpdate/@PostLoad. cc id={}", creditCard.getId());
    }
}
