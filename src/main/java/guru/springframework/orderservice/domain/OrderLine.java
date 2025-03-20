package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.util.Objects;

/**
 * Created by sergei on 05/03/2025
 */
@Entity
public class OrderLine extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderHeader orderHeader;

    @ManyToOne
    private Product product;

    private Integer quantityOrdered;

    @Version
    private Integer version;

    public OrderLine() {
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    /**
     * This method declared as private package.
     * Use {@link guru.springframework.orderservice.domain.OrderHeader#addOrderLine(OrderLine orderLine)} instead.
     */
    void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        if(quantityOrdered < 1) {
            throw new IllegalArgumentException("Quantity ordered must be equal or greater than 1. The value passed in is " + quantityOrdered);
        }
        this.quantityOrdered = quantityOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(quantityOrdered, orderLine.quantityOrdered) &&
                Objects.equals(orderHeader, orderLine.orderHeader) &&
                Objects.equals(product, orderLine.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderHeader.getId(), product, quantityOrdered);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                super.toString() +
                "quantityOrdered=" + quantityOrdered +
                ", orderHeader.id=" + orderHeader.getId() +
                '}';
    }
}
