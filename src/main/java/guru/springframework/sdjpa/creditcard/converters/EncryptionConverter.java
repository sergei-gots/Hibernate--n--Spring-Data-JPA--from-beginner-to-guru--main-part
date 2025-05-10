package guru.springframework.sdjpa.creditcard.converters;

import guru.springframework.sdjpa.creditcard.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sergei on 03/05/2025
 */
@Converter
public class EncryptionConverter implements AttributeConverter<String, String> {

   @Autowired
   EncryptionService encryptionService;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptionService.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptionService.decrypt(dbData);
    }

}
