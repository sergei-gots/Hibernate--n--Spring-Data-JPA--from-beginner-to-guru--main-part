package guru.springframework.sdjpa.creditcard.domain.pan;

import guru.springframework.sdjpa.creditcard.converters.EncryptionConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by sergei on 05/05/2025
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CreditCardPan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = EncryptionConverter.class)
    private String creditCardNumber;

    private Long creditCardId;

}
