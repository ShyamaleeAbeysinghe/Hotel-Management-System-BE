package hotel.system.grand.config;

import hotel.system.grand.util.EncryptionUtil;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class EncryptionConfig implements AttributeConverter<String,String> {
    private final EncryptionUtil encryptionUtil;

    @Override
    public String convertToDatabaseColumn(String s) {
        return encryptionUtil.encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return encryptionUtil.decrypt(s);
    }
}
