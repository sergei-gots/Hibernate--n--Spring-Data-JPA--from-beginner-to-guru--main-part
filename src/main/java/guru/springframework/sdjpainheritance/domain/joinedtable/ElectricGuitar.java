package guru.springframework.sdjpainheritance.domain.joinedtable;

import jakarta.persistence.Entity;

/**
 * Created by sergei on 02/04/2025
 */
@Entity
public class ElectricGuitar extends Guitar {

    private Integer numberOfPickups;

    public void setNumberOfPickups(Integer numberOfPickups) {
        this.numberOfPickups = numberOfPickups;
    }

    @Override
    public String toString() {
        return "ElectricGuitar{" +
                "numberOfPickups=" + numberOfPickups +
                "} " + super.toString();
    }
}
