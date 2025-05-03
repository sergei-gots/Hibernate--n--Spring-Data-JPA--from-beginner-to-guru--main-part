package guru.springframework.sdjpa.creditcard.domain;

import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 03/05/2025
 */
@Component
public class CreditCardJpaCallbackListener {

    @Autowired
    EncryptionService encryptionService;

    private static final Logger logger = LoggerFactory.getLogger(CreditCardJpaCallbackListener.class);

    @PrePersist
    @PreUpdate
    void onPrePersist(CreditCard creditCard) {
        logger.info("on @PrePersist/@PreUpdate. cc id={}", creditCard.getId());
        creditCard.setCreditCardNumber(encryptionService.encrypt(creditCard.getCreditCardNumber()));
        creditCard.setCvv(encryptionService.encrypt(creditCard.getCvv()));
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    void onPostLoad(CreditCard creditCard) {
        logger.info("on @PostPersist/@PostUpdate/@PostLoad. cc id={}", creditCard.getId());
    }
}
