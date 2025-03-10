package guru.springframework.jdbc.domain;

import guru.springframework.jdbc.enumeration.OrderStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @ManyToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;
    @Embedded
    private Address billingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader", cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    Set<OrderLine> orderLines;

    @OneToOne(cascade = CascadeType.PERSIST)
    private OrderApproval orderApproval;

    public OrderHeader() {
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * this method is declared as package-private.
     * Use instead the method
     * {@link guru.springframework.jdbc.domain.Customer#addOrderHeader(OrderHeader orderHeader)}
     */
    void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
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

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "id=" + id + '\'' +
                "customer='" + customer + '\'' +
                "order lines=" + orderLines +
                ", shippingAddress=" + shippingAddress + '\'' +
                ", billingAddress=" + billingAddress  + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderApproval=" + orderApproval +
                '}';
    }

    public void addOrderLine(OrderLine orderLine) {

        if (orderLines == null) {
            orderLines = new LinkedHashSet<>();
        }

        orderLine.setOrderHeader(this);
        orderLines.add(orderLine);
    }

}
