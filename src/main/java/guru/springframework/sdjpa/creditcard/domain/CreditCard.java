package guru.springframework.sdjpa.creditcard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Created by sergei on 26/04/2025
 */
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creditCardNumber;

    private String cvv;

    private String expirationDate;

    public CreditCard() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(id, that.id) && Objects.equals(creditCardNumber, that.creditCardNumber) && Objects.equals(cvv, that.cvv) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditCardNumber, cvv, expirationDate);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
