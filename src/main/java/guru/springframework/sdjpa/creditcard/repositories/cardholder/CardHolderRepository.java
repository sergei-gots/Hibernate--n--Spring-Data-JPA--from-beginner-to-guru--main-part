package guru.springframework.sdjpa.creditcard.repositories.cardholder;

import guru.springframework.sdjpa.creditcard.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 06/05/2025
 */
public interface CardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}
