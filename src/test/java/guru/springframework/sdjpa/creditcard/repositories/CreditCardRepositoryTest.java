package guru.springframework.sdjpa.creditcard.repositories;

import guru.springframework.sdjpa.creditcard.config.HibernatePropertiesAppender;
import guru.springframework.sdjpa.creditcard.domain.CreditCard;
import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import guru.springframework.sdjpa.creditcard.services.EncryptionServiceMimickingImpl;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import( {
        EncryptionServiceMimickingImpl.class,
        HibernatePropertiesAppender.class
} )
class CreditCardRepositoryTest {

    final String CREDIT_CARD_NUMBER = "123456789000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testSaveAndStoreCreditCard() {

        CreditCard cc = new CreditCard();

        cc.setCreditCardNumber(CREDIT_CARD_NUMBER);
        cc.setCvv("123");
        cc.setExpirationDate("12/2029");

        CreditCard savedCc = creditCardRepository.saveAndFlush(cc);

        System.out.println("Created CC-Number: " + cc.getCreditCardNumber());
        System.out.println("Encrypted CC-Number: " + encryptionService.encrypt(cc.getCreditCardNumber()));

        System.out.println("CC At Rest");
        System.out.println("Fetch the CreditCard from the database");

        Map<String, Object> dbRowQueriedWithJdbcTemplate = jdbcTemplate.queryForMap(
                "SELECT * FROM credit_card WHERE id = " + savedCc.getId()
        );

        String dbCcNumberValueQueriedWithJdbcTemplate = (String) dbRowQueriedWithJdbcTemplate.get("credit_card_number");

        CreditCard loadedCc = creditCardRepository.findById(savedCc.getId()).orElseThrow();
        CreditCard fetchedCc = creditCardRepository.findById(savedCc.getId()).orElseThrow();

        System.out.println("CreatedCc=" + cc);
        System.out.println("SavedCc=" + savedCc);
        System.out.println("loadedCc=" + loadedCc);
        System.out.println("fetched dbRowQueriedWithJdbcTemplate.dbCcNumberValueQueriedWithJdbcTemplate=" + dbCcNumberValueQueriedWithJdbcTemplate);
        System.out.println("Loaded cc class: " + loadedCc.getClass());
        System.out.println("Loaded cc class is HibernateProxy: " + (loadedCc instanceof HibernateProxy));

        assertEquals(savedCc.getCreditCardNumber(), loadedCc.getCreditCardNumber());

        assertEquals(dbCcNumberValueQueriedWithJdbcTemplate, encryptionService.encrypt(CREDIT_CARD_NUMBER));

        assertNotNull(fetchedCc);
        assertEquals(savedCc.getCreditCardNumber(), fetchedCc.getCreditCardNumber());


    }


}