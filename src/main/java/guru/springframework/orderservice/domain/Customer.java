package guru.springframework.orderservice.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sergei on 07/03/2025
 */
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "address.address", column = @Column(name = "address")),
        @AttributeOverride(name = "address.city", column = @Column(name = "city")),
        @AttributeOverride(name = "address.state", column = @Column(name = "state")),
        @AttributeOverride(name = "address.zipCode", column = @Column(name = "zip_code"))
})
public class Customer extends BaseEntity {

    private String customerName;

    @Embedded
    private Address address;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<OrderHeader> orderHeaders;

    @Version
    private Integer version;

    public Customer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderHeader> getOrderHeaders() {
        return orderHeaders;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName) && Objects.equals(address, customer.address) && Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName, address, phone, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", address='" + address.toString() + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }

    /**
     * declared as private package - use {@link guru.springframework.orderservice.domain.OrderHeader#setCustomer(Customer)} )} instead.
     * @throws IllegalArgumentException in case when customer hasn't been yet persisted
     * and does not  have its not null id.
     * @param orderHeader Order Header to add to the Customer's Order Header List
     */
    void addOrderHeader(OrderHeader orderHeader) {

        if (this.id == null) {
            throw new IllegalStateException("Before adding a new order header to the customer order header list save this customer entity to get 'customer_id' to set it in order_header first");
        }

        if (orderHeaders == null) {
            orderHeaders = new LinkedHashSet<>();
        }

        orderHeaders.add(orderHeader);


    }
}
