package guru.springframework.jdbc.domain;

import guru.springframework.jdbc.enumeration.OrderStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderHeader() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customer, that.customer) && Objects.equals(shippingAddress, that.shippingAddress) && Objects.equals(billingAddress, that.billingAddress) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer, shippingAddress, billingAddress, orderStatus);
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "id=" + id + '\'' +
                "customer='" + customer + '\'' +
                ", shippingAddress=" + shippingAddress + '\'' +
                ", billingAddress=" + billingAddress  + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }

}
