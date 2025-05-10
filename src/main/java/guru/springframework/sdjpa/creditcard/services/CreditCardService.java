package guru.springframework.sdjpa.creditcard.services;

import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;

/**
 * Created by sergei on 10/05/2025
 */
public interface CreditCardService {

    CreditCard saveCreditCard(CreditCard cc);

    CreditCard getCreditCardById(Long id);
}
