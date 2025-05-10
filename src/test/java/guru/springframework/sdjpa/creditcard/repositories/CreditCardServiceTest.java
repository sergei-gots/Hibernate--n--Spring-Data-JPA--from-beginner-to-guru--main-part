package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import guru.springframework.sdjpa.creditcard.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CreditCardServiceTest {

    final String TEST_CREDIT_CARD_NUMBER = "123456789000";
    final String TEST_CVV = "123";
    final String TEST_EXPIRATION_DATE = "09/29";

    @Autowired
    CreditCardService creditCardService;

    @Test
    public void testSaveAndFindByIdCreditCard() {

        CreditCard cc = CreditCard.builder()
                .creditCardNumber(TEST_CREDIT_CARD_NUMBER)
                .cvv(TEST_CVV)
                .expirationDate(TEST_EXPIRATION_DATE)
                .firstName("Sergei")
                .lastName("Gots")
                .zipCode("10785")
            .build();

        CreditCard savedCc = creditCardService.saveCreditCard(cc);

        assertNotNull(savedCc);
        assertNotNull(savedCc.getId());

        CreditCard fetchedCc = creditCardService.getCreditCardById(savedCc.getId());

        assertNotNull(fetchedCc);
        assertNotNull(fetchedCc.getId());
        assertNotNull(fetchedCc.getCreditCardNumber());
        assertNotNull(fetchedCc.getFirstName());
        assertNotNull(fetchedCc.getLastName());
        assertNotNull(fetchedCc.getZipCode());

    }


}