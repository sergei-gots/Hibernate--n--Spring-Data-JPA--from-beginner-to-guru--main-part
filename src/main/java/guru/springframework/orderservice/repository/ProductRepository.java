package guru.springframework.orderservice.repository;

import guru.springframework.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sergei on 04/03/2025
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByDescription(String description);
}
