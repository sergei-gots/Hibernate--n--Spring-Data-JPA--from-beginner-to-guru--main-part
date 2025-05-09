package guru.springframework.sdjpa.creditcard.domain.creditcard;

import guru.springframework.sdjpa.creditcard.converters.EncryptionConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sergei on 26/04/2025
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = EncryptionConverter.class)
    private String cvv;

    private String expirationDate;

    @Transient
    private String creditCardNumber;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    @Transient
    private String zipCode;

}
