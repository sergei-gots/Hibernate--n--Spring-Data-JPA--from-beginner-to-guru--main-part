package guru.springframework.jdbc.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Created by sergei on 02/03/2025
 */
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "shippingAddress.address", column = @Column(name = "shipping_address")),
        @AttributeOverride(name = "shippingAddress.city", column = @Column(name = "shipping_city")),
        @AttributeOverride(name = "shippingAddress.state", column = @Column(name = "shipping_state")),
        @AttributeOverride(name = "shippingAddress.zipCode", column = @Column(name = "shipping_zip_code")),
        @AttributeOverride(name = "billingAddress.address", column = @Column(name = "billing_address")),
        @AttributeOverride(name = "billingAddress.city", column = @Column(name = "billing_city")),
        @AttributeOverride(name = "billingAddress.state", column = @Column(name = "billing_state")),
        @AttributeOverride(name = "billingAddress.zipCode", column = @Column(name = "billing_zip_code")),
})
public class OrderHeader extends BaseEntity{

    private String customer;

    @Embedded
    private Address shippingAddress;
    @Embedded
    private Address billingAddress;

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
        return Objects.equals(customer, that.customer) && Objects.equals(shippingAddress, that.shippingAddress) && Objects.equals(billingAddress, that.billingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer, shippingAddress, billingAddress);
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "id=" + id + '\'' +
                "customer='" + customer + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                '}';
    }
}
