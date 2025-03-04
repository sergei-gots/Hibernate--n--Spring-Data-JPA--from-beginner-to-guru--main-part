package guru.springframework.jdbc.domain;

import guru.springframework.jdbc.enumeration.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

/**
 * Created by sergei on 04/03/2025
 */
@Entity
public class Product extends BaseEntity {

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    public Product() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(description, product.description) && productStatus == product.productStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, productStatus);
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", productStatus=" + productStatus +
                ", " + super.toString() +
                '}';
    }
}
