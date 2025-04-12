package guru.springframework.sdjpa.wordpress.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;

/**
 * Created by sergei on 12/04/2025
 */
@MappedSuperclass
public abstract class Meta {

    @Column(length = 255)
    @Size(max = 255)
    private String metaKey;

    @Lob
    private String metaValue;

}
