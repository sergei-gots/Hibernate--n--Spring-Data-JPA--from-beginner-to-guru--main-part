package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.domain.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    final String CREDIT_CARD_NUMBER = "123456789000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    public void testSaveAndStoreCreditCard() {

        CreditCard cC = new CreditCard();

        cC.setCreditCardNumber(CREDIT_CARD_NUMBER);
        cC.setCvv("123");
        cC.setExpirationDate("12/2029");

        CreditCard savedCc = creditCardRepository.saveAndFlush(cC);
        CreditCard fetchedCc = creditCardRepository.getReferenceById(savedCc.getId());

        assertNotNull(fetchedCc);
        assertEquals(CREDIT_CARD_NUMBER, fetchedCc.getCreditCardNumber());
    }


}