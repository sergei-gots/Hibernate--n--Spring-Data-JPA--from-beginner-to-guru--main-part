package guru.springframework.sdjpa.creditcard.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by sergei on 28/04/2025
 */
@Service
public class EncryptionServiceMimickingImpl implements EncryptionService {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionServiceMimickingImpl.class);

    private long encryptionInvocationCounter = 0L;

    @Override
    public String encrypt(String plainText) {

        logger.warn("encrypt(String plainText)");
        encryptionInvocationCounter++;
        return Base64.getEncoder().encodeToString(plainText.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String decrypt(String encryptedText) {
        logger.trace("decrypt(String encryptedText)");
        return new String(Base64.getDecoder().decode(encryptedText));
    }

    @Override
    public void resetEncryptionInvocationCounter() {
        encryptionInvocationCounter = 0L;
    }

    @Override
    public long getEncryptionInvocationCounter() {
        return encryptionInvocationCounter;
    }
}
