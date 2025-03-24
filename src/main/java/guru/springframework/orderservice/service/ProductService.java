package guru.springframework.orderservice.service;

import guru.springframework.orderservice.domain.Product;

/**
 * Created by sergei on 22/03/2025
 */
public interface ProductService {

    Product saveProduct(Product product);

    Product updateQuantityOnHand(Long productId, Integer quantityOnHand);
}
