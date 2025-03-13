package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sergei on 27/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class CustomerTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testAddOrderHeader() {

        OrderHeader orderHeader = new OrderHeader();

        Customer customer = new Customer();
        customer.setCustomerName("customerName");

        customerRepository.save(customer);

        orderHeader.setCustomer(customer);

        assertTrue(customer.getOrderHeaders().stream().anyMatch((that)->that.equals(orderHeader)));
    }

    @Test
    public void testAddOrderHeader_whenCustomerIsNotPersisted_thenThrows() {

        Customer customer = new Customer();
        OrderHeader orderHeader = new OrderHeader();
        assertThrows(IllegalStateException.class, () ->  orderHeader.setCustomer(customer));
    }

}