package guru.springframework.jdbc.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Created by sergei on 02/03/2025
 */
@Entity
public class OrderHeader extends BaseEntity{

    String customer;

    public OrderHeader() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer);
    }
}
