package guru.springframework.sdjpa.creditcard.repositories.pan;

import guru.springframework.sdjpa.creditcard.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sergei on 06/05/2025
 */
public interface PanRepository extends JpaRepository<CreditCardPan, Long > {

    Optional<CreditCardPan> findByCreditCardId(Long id);

}
