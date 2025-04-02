package guru.springframework.sdjpainheritance.domain.joinedtable;

import jakarta.persistence.Entity;

/**
 * Created by sergei on 02/04/2025
 */
@Entity
public class Piano extends Instrument {

    private Integer numberOfKeys;
}
