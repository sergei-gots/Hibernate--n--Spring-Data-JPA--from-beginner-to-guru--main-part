package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.Address;
import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.repository.CustomerRepository;
import guru.springframework.orderservice.repository.OrderHeaderRepository;
import guru.springframework.orderservice.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sergei on 13/03/2025
 */
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {

    final String PRODUCT_DESCRIPTION_1 = "Product 1";
    final String PRODUCT_DESCRIPTION_2 = "Product 2";
    final String PRODUCT_DESCRIPTION_3 = "Product 3";

    final String TEST_CUSTOMER = "Test Customer";

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Commit
    @Disabled
    void testDataLoader() {

        List<Product> products = loadProducts();
        Customer customer = loadCustomer();

        final int ORDERS_TO_CREATE = 20;

        for (int i = 0; i < ORDERS_TO_CREATE; i++) {
            System.out.println("Creating order #:" + i);
            saveOrder(customer, products);
        }

        orderHeaderRepository.flush();
    }

    private List<Product> loadProducts() {

        List<Product> products = new ArrayList<>(3);

        products.add(getOrSaveProduct(PRODUCT_DESCRIPTION_1));
        products.add(getOrSaveProduct(PRODUCT_DESCRIPTION_2));
        products.add(getOrSaveProduct(PRODUCT_DESCRIPTION_2));

        return  products;
    }

    private Product getOrSaveProduct(String productDescription) {

        return productRepository.findProductByDescription(productDescription).
                orElseGet(() -> saveProduct(productDescription));
    }

    private Product saveProduct(String productDescription) {

        Product product = new Product();
        product.setDescription(productDescription);

        return productRepository.save(product);
    }

    private OrderHeader saveOrder(Customer customer, List<Product> products) {

        Random random = new Random();

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        products.forEach(product -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantityOrdered(random.nextInt(1,20));
            orderHeader.addOrderLine(orderLine);
        });

        return orderHeaderRepository.save(orderHeader);
    }

    private Customer loadCustomer() { return getOrSaveCustomer(TEST_CUSTOMER); }

    private Customer getOrSaveCustomer(String customerName) {
        return customerRepository.findCustomerByCustomerNameIgnoreCase(customerName)
                .orElseGet(() -> saveCustomer(customerName));
    }

    private Customer saveCustomer(String customerName) {

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setEmail("test@example.com");
        Address address = new Address();
        address.setAddress("123 Main");
        address.setCity("New Orleans");
        address.setState("LA");
        customer.setAddress(address);

        return customerRepository.save(customer);
    }
}
