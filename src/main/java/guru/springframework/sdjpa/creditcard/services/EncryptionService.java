package guru.springframework.sdjpa.creditcard.services;

/**
 * Created by sergei on 28/04/2025
 */
public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);

}
