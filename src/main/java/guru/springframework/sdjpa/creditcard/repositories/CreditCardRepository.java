package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 26/04/2025
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
