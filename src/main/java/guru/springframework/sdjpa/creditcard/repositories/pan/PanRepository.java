package guru.springframework.sdjpa.creditcard.repositories.pan;

import guru.springframework.sdjpa.creditcard.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 06/05/2025
 */
public interface PanRepository extends JpaRepository<CreditCardPan, Long > {
}
