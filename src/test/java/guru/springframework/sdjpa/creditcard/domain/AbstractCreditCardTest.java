package guru.springframework.sdjpa.creditcard.domain;

import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;

/**
 * Created by sergei on 10/05/2025
 */
public class AbstractCreditCardTest {

    public final String TEST_CREDIT_CARD_NUMBER = "123456789000";
    public final String TEST_CVV = "123";
    public final String TEST_EXPIRATION_DATE = "09/29";

    public CreditCard buildTestCreditCard() {
        return CreditCard.builder()
                .creditCardNumber(TEST_CREDIT_CARD_NUMBER)
                .cvv(TEST_CVV)
                .expirationDate(TEST_EXPIRATION_DATE)
                .firstName("Sergei")
                .lastName("Gots")
                .zipCode("10785")
                .build();
    }
}
