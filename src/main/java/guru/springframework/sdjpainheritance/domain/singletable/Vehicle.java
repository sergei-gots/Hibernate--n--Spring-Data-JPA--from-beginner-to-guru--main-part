package guru.springframework.sdjpainheritance.domain.singletable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Created by sergei on 27/03/2025
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//by default name of the @DiscriminatorColumn is 'dtype'
//@DiscriminatorColumn(name = "vehicle_type")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
