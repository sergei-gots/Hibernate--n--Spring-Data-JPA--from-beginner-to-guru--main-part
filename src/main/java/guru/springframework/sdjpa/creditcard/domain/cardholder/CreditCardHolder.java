package guru.springframework.sdjpa.creditcard.domain.cardholder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sergei on 05/05/2025
 */
@Entity(name = "card_holder")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CreditCardHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String zipCode;

    private Long creditCardId;

}
