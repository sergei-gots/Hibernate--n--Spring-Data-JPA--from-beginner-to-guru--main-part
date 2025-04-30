package guru.springframework.sdjpa.creditcard.domain;

import guru.springframework.sdjpa.creditcard.annotations.EncryptedString;
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

    @EncryptedString
    private String creditCardNumber;

    @EncryptedString
    private String cvv;

    private String expirationDate;

    public CreditCard() {
    }

    public Long getId() {
        return id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
