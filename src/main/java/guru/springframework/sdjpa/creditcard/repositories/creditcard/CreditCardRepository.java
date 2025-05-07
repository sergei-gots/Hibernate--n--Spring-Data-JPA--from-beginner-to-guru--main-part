package guru.springframework.sdjpa.creditcard.repositories.creditcard;

import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 26/04/2025
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
