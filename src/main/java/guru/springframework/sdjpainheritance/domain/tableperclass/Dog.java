package guru.springframework.sdjpainheritance.domain.tableperclass;

import jakarta.persistence.Entity;

/**
 * Created by sergei on 27/03/2025
 */
@Entity
public class Dog extends Mammal{

    private String breed;
}
