package guru.springframework.orderservice.service;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sergei on 22/03/2025
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Product updateQuantityOnHand(Long productId, Integer quantityOnHand) {

        Product product = productRepository.findById(productId).orElseThrow();

        product.setQuantityOnHand(quantityOnHand);

        return productRepository.saveAndFlush(product);
    }
}
