package guru.springframework.sdjpainheritance.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Created by sergei on 27/03/2025
 */
@Entity
//Value of @DiscrimiatorValue by default is going to be the class name. Here it would be 'car'
@DiscriminatorValue("PKW")
public class Car extends Vehicle {

    private String trimLevel;
}
