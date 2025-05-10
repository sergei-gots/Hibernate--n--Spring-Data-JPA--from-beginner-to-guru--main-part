package guru.springframework.sdjpa.creditcard.services;

import guru.springframework.sdjpa.creditcard.domain.cardholder.CreditCardHolder;
import guru.springframework.sdjpa.creditcard.domain.creditcard.CreditCard;
import guru.springframework.sdjpa.creditcard.domain.pan.CreditCardPan;
import guru.springframework.sdjpa.creditcard.repositories.cardholder.CardHolderRepository;
import guru.springframework.sdjpa.creditcard.repositories.creditcard.CreditCardRepository;
import guru.springframework.sdjpa.creditcard.repositories.pan.PanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sergei on 10/05/2025
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    PanRepository panRepository;

    @Autowired
    CardHolderRepository cardHolderRepository;

    @Transactional
    @Override
    public CreditCard saveCreditCard(CreditCard cc) {

        CreditCard savedCc = creditCardRepository.save(cc);
        savedCc.setCreditCardNumber(cc.getCreditCardNumber());
        savedCc.setFirstName(cc.getFirstName());
        savedCc.setLastName(cc.getLastName());
        savedCc.setZipCode(cc.getZipCode());

        CreditCardPan pan = CreditCardPan.builder()
                .creditCardId(savedCc.getId())
                .creditCardNumber(cc.getCreditCardNumber())
                .build();
        panRepository.save(pan);

        CreditCardHolder cardHolder = CreditCardHolder.builder()
                .creditCardId(savedCc.getId())
                .firstName(savedCc.getFirstName())
                .lastName(savedCc.getFirstName())
                .zipCode(savedCc.getZipCode())
                .build();
        cardHolderRepository.save(cardHolder);

        return savedCc;
    }

    @Transactional
    @Override
    public CreditCard getCreditCardById(Long ccId) {

        CreditCard cc = creditCardRepository.findById(ccId).orElseThrow();
        CreditCardPan pan = panRepository.findByCreditCardId(ccId).orElseThrow();
        CreditCardHolder cardHolder = cardHolderRepository.findByCreditCardId(ccId).orElseThrow();

        cc.setCreditCardNumber(pan.getCreditCardNumber());
        cc.setFirstName(cardHolder.getFirstName());
        cc.setLastName(cardHolder.getLastName());
        cc.setZipCode(cardHolder.getZipCode());

        return cc;

    }

}
