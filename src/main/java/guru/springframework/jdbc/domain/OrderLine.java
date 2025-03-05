package guru.springframework.jdbc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

/**
 * Created by sergei on 05/03/2025
 */
@Entity
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderHeader orderHeader;

    public OrderLine() {
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

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(quantityOrdered, orderLine.quantityOrdered) && Objects.equals(orderHeader, orderLine.orderHeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantityOrdered, orderHeader.getId());
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
