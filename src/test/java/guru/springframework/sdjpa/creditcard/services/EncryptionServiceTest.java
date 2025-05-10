package guru.springframework.sdjpa.creditcard.services;

import guru.springframework.sdjpa.creditcard.converters.EncryptionConverter;
import guru.springframework.sdjpa.creditcard.domain.AbstractCreditCardTest;
import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import guru.springframework.sdjpa.creditcard.repositories.cardholder.CardHolderRepository;
import guru.springframework.sdjpa.creditcard.repositories.creditcard.CreditCardRepository;
import guru.springframework.sdjpa.creditcard.repositories.pan.PanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EncryptionServiceTest extends AbstractCreditCardTest {

    @InjectMocks
    private CreditCardServiceImpl creditCardServiceWithMockedConverter;

    @Mock
    private EncryptionConverter encryptionConverter;

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private PanRepository panRepository;

    @Mock
    private CardHolderRepository cardHolderRepository;

    @Test
    public void testEncryptionIsPerformed_whenSavingCreditCard() {

        CreditCard cc = buildTestCreditCard();
        cc.setId(1L);

        when(creditCardRepository.save(cc)).thenReturn(cc);
        creditCardServiceWithMockedConverter.saveCreditCard(cc);
        //ToDo
        verify(encryptionConverter, times(2)).convertToDatabaseColumn(any());


    }


}