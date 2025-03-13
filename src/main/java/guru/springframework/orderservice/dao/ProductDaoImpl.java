package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.enumeration.ProductStatus;
import guru.springframework.orderservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sergei on 04/03/2025
 */
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    public ProductDaoImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {

        if (product.getProductStatus() == null) {
            product.setProductStatus(ProductStatus.NEW);
        }
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    public Product update(Product product) {

        return productRepository.save(product);

    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public Product findProductByDescription(String description) {

        return productRepository.findProductByDescription(description)
                .orElseThrow(EntityNotFoundException::new);
    }
}
