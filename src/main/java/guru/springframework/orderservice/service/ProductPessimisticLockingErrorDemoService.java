package guru.springframework.orderservice.service;

import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.enumeration.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sergei on 22/03/2025
 */
@Service
public class ProductPessimisticLockingErrorDemoService {

    private final Integer DEMO_QUANTITY_ON_HAND = 25;

    @Autowired
    private ProductService productService;

    public void testUpdateQuantityOnHand() {
        Product product = new Product();
        product.setDescription("Product Description #1");
        product.setProductStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productService.saveProduct(product);

        Product updatedProduct = productService.updateQuantityOnHand(savedProduct.getId(), DEMO_QUANTITY_ON_HAND);

        System.out.println(updatedProduct);
    }
}
