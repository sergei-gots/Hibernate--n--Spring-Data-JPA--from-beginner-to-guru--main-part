package guru.springframework.sdjpainheritance.domain.msuper;

import jakarta.persistence.Entity;

/**
 * Created by sergei on 26/03/2025
 */
@Entity
public class OrderHeader extends BaseEntity {

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
