package guru.springframework.sdjpainheritance.domain.joinedtable;

import jakarta.persistence.Entity;

/**
 * Created by sergei on 02/04/2025
 */
@Entity
public class Guitar extends Instrument{

    private Integer numberOfStrings;

    public void setNumberOfStrings(Integer numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "numberOfStrings=" + numberOfStrings +
                "} " + super.toString();
    }
}
