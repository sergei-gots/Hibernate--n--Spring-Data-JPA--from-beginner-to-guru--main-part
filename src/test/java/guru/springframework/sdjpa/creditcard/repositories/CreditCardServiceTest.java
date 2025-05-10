package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.config.TestCardDatabaseConfiguration;
import guru.springframework.sdjpa.creditcard.config.TestCardHolderDatabaseConfiguration;
import guru.springframework.sdjpa.creditcard.config.TestPanDatabaseConfiguration;
import guru.springframework.sdjpa.creditcard.config.flyway.TestCardFlywayConfiguration;
import guru.springframework.sdjpa.creditcard.config.flyway.TestCardHolderFlywayConfiguration;
import guru.springframework.sdjpa.creditcard.config.flyway.TestPanFlywayConfiguration;
import guru.springframework.sdjpa.creditcard.domain.AbstractCreditCardTest;
import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import guru.springframework.sdjpa.creditcard.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {
        TestCardFlywayConfiguration.class,
        TestCardDatabaseConfiguration.class,
        TestCardHolderFlywayConfiguration.class,
        TestCardHolderDatabaseConfiguration.class,
        TestPanFlywayConfiguration.class,
        TestPanDatabaseConfiguration.class
})
@Transactional
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardServiceTest extends AbstractCreditCardTest {

    @Autowired
    CreditCardService creditCardService;

    @Rollback
    @Test
    @Transactional
    public void testSaveAndFindByIdCreditCard() {

        CreditCard cc = buildTestCreditCard();

        CreditCard savedCc = creditCardService.saveCreditCard(cc);

        assertNotNull(savedCc);
        assertNotNull(savedCc.getId());

        System.out.println("savedCc = " + savedCc);
        CreditCard fetchedCc = creditCardService.getCreditCardById(savedCc.getId());

        assertNotNull(fetchedCc);
        assertNotNull(fetchedCc.getId());
        assertNotNull(fetchedCc.getCreditCardNumber());
        assertNotNull(fetchedCc.getFirstName());
        assertNotNull(fetchedCc.getLastName());
        assertNotNull(fetchedCc.getZipCode());

    }


}