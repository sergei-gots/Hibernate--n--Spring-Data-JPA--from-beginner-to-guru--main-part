package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.domain.CreditCard;
import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import guru.springframework.sdjpa.creditcard.services.EncryptionServiceMimickingImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(EncryptionServiceMimickingImpl.class)
class CreditCardRepositoryTest {

    final String CREDIT_CARD_NUMBER = "123456789000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionService;

    @Test
    public void testSaveAndStoreCreditCard() {

        CreditCard cC = new CreditCard();

        System.out.println("CREDIT_CARD_NUMBER: " + CREDIT_CARD_NUMBER);

        String encryptedCcNumber = encryptionService.encrypt(CREDIT_CARD_NUMBER);

        System.out.println("encryptedCcNumber : " + encryptedCcNumber);


        cC.setCreditCardNumber(encryptedCcNumber);
        cC.setCvv("123");
        cC.setExpirationDate("12/2029");

        System.out.println("Save and Store the CreditCard to the database");
        CreditCard savedCc = creditCardRepository.saveAndFlush(cC);

        System.out.println("Fetch the CreditCard from the database");
        CreditCard fetchedCc = creditCardRepository.getReferenceById(savedCc.getId());

        assertNotNull(fetchedCc);

        String decryptedCcNumber = encryptionService.decrypt(fetchedCc.getCreditCardNumber());

        assertEquals(CREDIT_CARD_NUMBER, decryptedCcNumber);
    }


}