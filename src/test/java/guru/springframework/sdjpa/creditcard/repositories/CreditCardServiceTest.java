package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.converters.EncryptionConverter;
import guru.springframework.sdjpa.creditcard.domain.AbstractCreditCardTest;
import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import guru.springframework.sdjpa.creditcard.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CreditCardServiceTest extends AbstractCreditCardTest {

    @Autowired
    CreditCardService creditCardService;

    @Mock
    private EncryptionConverter encryptionConverter;

    @Test
    public void testSaveAndFindByIdCreditCard() {

        CreditCard cc = buildTestCreditCard();

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