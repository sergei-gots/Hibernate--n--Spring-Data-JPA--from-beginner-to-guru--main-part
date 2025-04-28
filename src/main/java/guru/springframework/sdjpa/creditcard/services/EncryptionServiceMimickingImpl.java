package guru.springframework.sdjpa.creditcard.services;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by sergei on 28/04/2025
 */
@Service
public class EncryptionServiceMimickingImpl implements EncryptionService {

    @Override
    public String encrypt(String freeText) {
        return Base64.getEncoder().encodeToString(freeText.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String decrypt(String encryptedText) {
        return new String(Base64.getDecoder().decode(encryptedText));
    }

}
