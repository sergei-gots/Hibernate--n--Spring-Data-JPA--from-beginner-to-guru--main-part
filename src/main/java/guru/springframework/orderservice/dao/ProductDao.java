package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sergei on 04/03/2025
 */
public interface ProductDao {

    Product save(Product product);

    Product getById(Long id);

    Page<Product> findAll(Pageable pageable);

    Product update(Product product);

    void deleteById(Long id);

    Product findProductByDescription(String description);
}
