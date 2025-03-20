package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.domain.Category;
import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.enumeration.ProductStatus;
import guru.springframework.orderservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sergei on 04/03/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    ProductDao productDao;

    @BeforeEach
    public void setUp() {
        productDao = new ProductDaoImpl(productRepository);
    }

    @Test
    public void testEquals() {

        String title = RandomString.make(7);

        Product product1 = new Product();
        product1.setDescription(title);

        Product product2 = new Product();
        product2.setDescription(title);
        product2.setQuantityOnHand(product1.getQuantityOnHand() + 1);

        assertThat(product1).isEqualTo(product2);
    }

    @Test
    public void testNotEquals() {

        Product product1 = new Product();
        product1.setDescription(RandomString.make(7));

        Product product2 = new Product();
        product2.setDescription(RandomString.make(8));

        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    public void testSave() {

        Product product = new Product();
        product.setDescription("Description#" + RandomString.make(10));
  
        Product saved = productDao.save(product);
        product.setProductStatus(ProductStatus.IN_STOCK);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDescription()).isEqualTo(product.getDescription());
        assertEquals(0, saved.getQuantityOnHand());

        assertNotNull(saved.getCreatedDate());
        assertNotNull(saved.getLastModifiedDate());

        long timeDiffMillis = saved.getLastModifiedDate().getTime() - saved.getCreatedDate().getTime();
        assertThat(timeDiffMillis).isLessThan(10);
    }

    @Test
    public void testGetById() {

        Product product = new Product();
        product.setDescription("DescriptionName##");
        Product saved = productDao.save(product);

        Product fetched = productDao.getById(saved.getId());

        assertThat(fetched).isNotNull();
        assertThat(fetched).isEqualTo(saved);
    }

    @Test
    public void testGetById_whenNotExists_thenThrows() {

        assertThrows(EntityNotFoundException.class, () -> productDao.getById(Long.MAX_VALUE));
    }

    @Test
    public void TestFindAll() {

        int pageSize = 10;

        Pageable pageable = PageRequest.of(1, pageSize);

        Page<Product> productsPage = productDao.findAll(pageable);

        assertThat(productsPage).isNotNull();

        List<Product> products = productsPage.getContent();

        assertThat(products.size()).isGreaterThanOrEqualTo(0);
        assertThat(products.size()).isLessThanOrEqualTo(pageSize);
    }

    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setDescription("Description#1" + RandomString.make(10));

        product.setDescription("Description#" + RandomString.make(10));

        Product savedProduct = productDao.save(product);

        savedProduct.setDescription("Description#2" + RandomString.make(10));
        savedProduct.setProductStatus(ProductStatus.IN_STOCK);
        savedProduct.setQuantityOnHand(Integer.MAX_VALUE);

        Product updatedProduct = productDao.update(savedProduct);

        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct).isEqualTo(savedProduct);
        assertThat(updatedProduct.getProductStatus()).isEqualTo(savedProduct.getProductStatus());
        assertEquals(Integer.MAX_VALUE, updatedProduct.getQuantityOnHand());

        assertNotNull(updatedProduct.getCreatedDate());
        assertNotNull(updatedProduct.getLastModifiedDate());
        assertThat(updatedProduct.getCreatedDate()).isNotEqualTo(updatedProduct.getLastModifiedDate());

    }

    @Test
    public void testDeleteById() {

        Product product = new Product();
        product.setDescription("Description#" + RandomString.make(10));

        Product saved = productDao.save(product);

        productDao.deleteById(saved.getId());

        assertThrows(EntityNotFoundException.class, () -> productDao.getById(saved.getId()));
    }

    @Test
    public void testGetByDescriptionName() {

        String customerName = "Description#" + RandomString.make(10);

        Product product = new Product();
        product.setDescription(customerName);

        Product saved = productDao.save(product);

        Product fetched = productDao.findProductByDescription(customerName);

        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(saved.getId());
        assertThat(fetched.getDescription()).isEqualTo(customerName);
    }

    @Test
    public void testGetByDescriptionName_whenNotExists_thenThrows() {

        String customerName = "Description#that#is#not#in#db" + RandomString.make(10);

        assertThrows(EntityNotFoundException.class, () -> productDao.findProductByDescription(customerName));

    }

    @Test
    public void testGetByDescription_checkCategory() {

        Product fetched = productDao.findProductByDescription("Product 1");

        assertNotNull(fetched);
        assertEquals("Product 1", fetched.getDescription());

        Set<Category> categories = fetched.getCategories();
        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertTrue(
                categories.stream()
                        .anyMatch(category ->
                            category.getDescription().equals("Category 2") &&
                                    category.getProducts().size() == 2 &&
                                    category.getProducts().stream()
                                            .anyMatch(product -> product.getDescription().equals("Product 1"))
                        )
        );
    }

}