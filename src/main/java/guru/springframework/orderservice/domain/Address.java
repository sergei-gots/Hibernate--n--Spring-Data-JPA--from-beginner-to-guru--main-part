package guru.springframework.orderservice.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Created by sergei on 03/03/2025
 */
@Embeddable
public class Address {

    @Size(max = 50)
    private String address;

    @Size(max = 50)
    private String city;

    @Size(max = 30)
    private String state;

    @Size(max = 10)
    private String zipCode;

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zip_code) {
        this.zipCode = zip_code;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) && Objects.equals(city, address1.city) && Objects.equals(state, address1.state) && Objects.equals(zipCode, address1.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, state, zipCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
