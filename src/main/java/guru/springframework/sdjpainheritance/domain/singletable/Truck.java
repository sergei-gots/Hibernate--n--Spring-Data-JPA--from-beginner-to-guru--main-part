package guru.springframework.sdjpainheritance.domain.singletable;

import jakarta.persistence.Entity;

/**
 * Created by sergei on 27/03/2025
 */
@Entity
public class Truck extends Vehicle {
    private Integer payload;
}
