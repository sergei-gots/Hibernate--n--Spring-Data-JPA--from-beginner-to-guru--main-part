package guru.springframework.jdbc.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sergei on 07/03/2025
 */
@Entity
public class Customer extends BaseEntity {

    private String customerName;

    private String address;

    private  String city;

    private String state;

    private String zip_code;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<OrderHeader> orderHeaders;

    public Customer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName) && Objects.equals(address, customer.address) && Objects.equals(city, customer.city) && Objects.equals(state, customer.state) && Objects.equals(zip_code, customer.zip_code) && Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName, address, city, state, zip_code, phone, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }

    /**
     * @throws IllegalArgumentException in case when customer hasn't been yet persisted
     * and doesn't not have its not null id.
     * @param orderHeader Order Header to add to the Customer's Order Header List
     */
    public void addOrderHeader(OrderHeader orderHeader) {

        if (this.id == null) {
            throw new IllegalStateException("Before adding a new order header to the customer order header list save this customer entity to get 'customer_id' to set it in order_header first");
        }

        orderHeader.setCustomer(this);

        if (orderHeaders == null) {
            orderHeaders = new LinkedHashSet<>();
        }

        orderHeaders.add(orderHeader);


    }
}
