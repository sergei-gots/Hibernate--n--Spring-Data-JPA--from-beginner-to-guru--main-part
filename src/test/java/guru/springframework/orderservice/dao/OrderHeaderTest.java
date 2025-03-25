package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.DataLoadTest;
import guru.springframework.orderservice.domain.Address;
import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderApproval;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import guru.springframework.orderservice.domain.Product;
import guru.springframework.orderservice.enumeration.OrderStatus;
import guru.springframework.orderservice.enumeration.ProductStatus;
import guru.springframework.orderservice.repository.CustomerRepository;
import guru.springframework.orderservice.repository.OrderApprovalRepository;
import guru.springframework.orderservice.repository.OrderHeaderRepository;
import guru.springframework.orderservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import net.bytebuddy.utility.RandomString;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sergei on 27/02/2025
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.springframework.jdbc.dao")
public class OrderHeaderTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderApprovalRepository orderApprovalRepository;

    OrderHeaderDao orderHeaderDao;

    Product product;
    Customer customer;

    @BeforeEach
    public void setUp() {

        orderHeaderDao = new OrderHeaderDaoImpl(orderHeaderRepository);

        product = new Product();
        product.setProductStatus(ProductStatus.NEW);
        product.setDescription("Test Product");
        product = productRepository.save(product);

        customer = new Customer();
        customer.setCustomerName("Test Customer");
        customer = customerRepository.save(customer);
    }

    @Test
    public void testEquals() {

        OrderHeader orderHeader1 = new OrderHeader();
        orderHeader1.setCustomer(customer);

        OrderHeader orderHeader2 = new OrderHeader();
        orderHeader2.setCustomer(customer);

        assertThat(orderHeader1).isEqualTo(orderHeader2);
    }

    @Test
    public void testEquals_whenCustomerDiffer() {

        OrderHeader orderHeader1 = new OrderHeader();
        Customer customer1 = new Customer();
        customer1.setCustomerName(RandomString.make(7));
        customerRepository.save(customer1);
        orderHeader1.setCustomer(customer1);

        OrderHeader orderHeader2 = new OrderHeader();
        Customer customer2 = new Customer();
        customer2.setCustomerName(RandomString.make(7));
        customerRepository.save(customer2);
        orderHeader2.setCustomer(customer2);

        assertEquals(orderHeader1, orderHeader2);
    }

    @Test
    public void testSave_withOrderApproval() {

        OrderHeader orderHeader = new OrderHeader();
        customerRepository.save(customer);
        orderHeader.setCustomer(customer);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        orderHeader.setOrderApproval(orderApproval);

        OrderHeader saved = orderHeaderDao.save(orderHeader);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(saved.getCustomer(), orderHeader.getCustomer());

        assertEquals(saved.getOrderApproval(), orderHeader.getOrderApproval());

    }

    @Test
    public void testSave_basic() {

        Address address = createTestAddress();

        OrderHeader orderHeader = new OrderHeader();

        Address customerAddress = createTestAddress();
        customer.setAddress(customerAddress);

        orderHeader.setCustomer(customer);

        orderHeader.setShippingAddress(address);
        orderHeader.setBillingAddress(address);

        OrderHeader saved = orderHeaderDao.save(orderHeader);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(saved.getCustomer(), orderHeader.getCustomer());
        assertEquals(saved.getBillingAddress(), orderHeader.getBillingAddress());
        assertEquals(saved.getShippingAddress(), orderHeader.getShippingAddress());

        assertNotNull(saved.getCreatedDate());
        assertNotNull(saved.getLastModifiedDate());

        long timeDiffMillis = saved.getLastModifiedDate().getTime() - saved.getCreatedDate().getTime();
        assertThat(timeDiffMillis).isLessThan(10);
    }

    @Test
    public void testSaveWithOrderHeaderAndOrderLine() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantityOrdered(1);
        orderHeader.addOrderLine(orderLine);

        OrderHeader savedOrder = orderHeaderDao.save(orderHeader);

        assertEquals(orderHeader, savedOrder);
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(1, savedOrder.getOrderLines().size());

        OrderLine orderLine1 = savedOrder.getOrderLines().stream().findFirst().orElse(null);

        assertNotNull(orderLine1);
        assertNotNull(orderLine1.getId());
        assertNotNull(orderLine1.getProduct());
        assertEquals(product, orderLine1.getProduct());
        assertEquals(1, orderLine1.getQuantityOrdered());

        assertNotNull(orderLine1.getOrderHeader());
        assertNotNull(orderLine1.getOrderHeader().getId());
        assertNotNull(orderLine1.getOrderHeader().getOrderLines());
        assertEquals(product, orderLine1.getProduct());

    }

    @Test
    public void testSave_fillCustomerPropertiesMaxLength() {

        Address address = createTestAddress();

        OrderHeader orderHeader = new OrderHeader();

        String customerName = RandomString.make(50);
        String phone = RandomString.make(20);
        String email = "test-customer456782012345678301234567840@a.dot.com";
        String zip_code = RandomString.make(10);
        String state = RandomString.make(10);
        String city = RandomString.make(50);
        String addressLine = RandomString.make(50);

        customer.setCustomerName(customerName);
        customer.setPhone(phone);
        customer.setEmail(email);

        Address customerAddress = createTestAddress();
        customerAddress.setZipCode(zip_code);
        customerAddress.setCity(city);
        customerAddress.setAddress(addressLine);
        customerAddress.setState(state);
        customer.setAddress(customerAddress);

        orderHeader.setCustomer(customer);

        orderHeader.setShippingAddress(address);
        orderHeader.setBillingAddress(address);

        OrderHeader savedOrderHeader = orderHeaderDao.save(orderHeader);
        Customer savedCustomer = savedOrderHeader.getCustomer();

        assertNotNull(savedCustomer);
        assertEquals(customerName, savedCustomer.getCustomerName());
        assertEquals(phone, savedCustomer.getPhone());
        assertEquals(email, savedCustomer.getEmail());
        assertEquals(customerAddress, savedCustomer.getAddress());
    }

    @Test
    public void testSave_throw_whenPropertiesAreTooLong() {

        Address address = createTestAddress();

        OrderHeader orderHeader = new OrderHeader();
        customer.setCustomerName(RandomString.make(51));
        customer.setEmail(RandomString.make(51));
        customer.setPhone(RandomString.make(21));

        Address customerAddress = createTestAddress();
        customerAddress.setZipCode(RandomString.make(11));
        customerAddress.setState(RandomString.make(31));
        customerAddress.setCity(RandomString.make(51));
        customerAddress.setAddress(RandomString.make(51));
        customer.setAddress(customerAddress);

        orderHeader.setCustomer(customer);

        orderHeader.setShippingAddress(address);
        orderHeader.setBillingAddress(address);

        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> orderHeaderDao.save(orderHeader));

        assertEquals(8, exception.getConstraintViolations().size());
    }


    @Test
    public void testGetById() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        OrderHeader saved = orderHeaderRepository.save(orderHeader);

        OrderHeader fetched = orderHeaderDao.getById(saved.getId());

        assertThat(fetched).isNotNull();
        assertThat(fetched).isEqualTo(saved);
        assertEquals(customer.getCustomerName(), orderHeader.getCustomer().getCustomerName());
    }

    @Test
    @Disabled
    public void testGetBySpecifiedId() {

        OrderHeader fetched = orderHeaderDao.getById(12_122L);

        assertNotNull(fetched);
        assertNotNull(fetched.getCustomer());
        assertNotNull(fetched.getCustomer().getCustomerName());
    }


    @Test
    public void testGetById_whenNotExists_thenThrows() {

        assertThrows(EntityNotFoundException.class, () -> orderHeaderDao.getById(Long.MAX_VALUE));
    }

    @Test
    public void TestFindAll() {

        int pageSize = 10;

        Pageable pageable = PageRequest.of(1, pageSize);

        Page<OrderHeader> orderHeadersPage = orderHeaderDao.findAll(pageable);

        assertThat(orderHeadersPage).isNotNull();

        List<OrderHeader> orderHeaders = orderHeadersPage.getContent();

        assertThat(orderHeaders.size()).isGreaterThanOrEqualTo(0);
        assertThat(orderHeaders.size()).isLessThanOrEqualTo(pageSize);
    }

    @Test
    public void testUpdate() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        Address address = createTestAddress();
        orderHeader.setBillingAddress(address);

        OrderHeader persisted = orderHeaderDao.save(orderHeader);

        orderHeader.setOrderStatus(OrderStatus.DELIVERED);

        OrderHeader updated = orderHeaderDao.update(persisted);

        assertThat(updated).isNotNull();
        assertThat(updated).isEqualTo(persisted);
        assertThat(updated.getOrderStatus()).isEqualTo(persisted.getOrderStatus());

        assertNotNull(updated.getCreatedDate());
        assertNotNull(updated.getLastModifiedDate());
        assertThat(updated.getCreatedDate()).isNotEqualTo(updated.getLastModifiedDate());

    }

    @Test
    public void testUpdate_setOrderApproval() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertEquals(orderHeader, savedOrder);
        assertNull(orderHeader.getOrderApproval());

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        savedOrder.setOrderApproval(orderApproval);

        OrderHeader updatedOrder = orderHeaderRepository.save(savedOrder);

        assertEquals(orderHeader, updatedOrder);
        assertNotNull(orderHeader.getOrderApproval());
        assertEquals("me", orderHeader.getOrderApproval().getApprovedBy());


    }

    @Test
    public void testUpdate_setOrderApprovalNull_checkOrphanRemoval() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        orderHeader.setOrderApproval(orderApproval);

        orderHeaderRepository.save(orderHeader);

        assertNotNull(orderApproval.getId());

        orderHeader.setOrderApproval(null);
        orderHeaderRepository.flush();

        assertThrows(EntityNotFoundException.class, () -> {
            OrderApproval proxyOrderApproval = orderApprovalRepository.getReferenceById(orderApproval.getId());
            Hibernate.initialize(proxyOrderApproval);
        });
    }

    /**
     * From dBeaver (or other client, run the following SQL statement, then test below.)
     * Once you commit, the test will complete.
     * If test completes immediately, check autocommit settings in client.
     *  {@code
     *      -- Disable auto-commit
     *      SET AUTOCOMMIT = false;
     *      -- Check auto-commit
     *      SELECT @@autocommit;
     *     -- Start new transaction
     *     START TRANSACTION;
     *     -- Lock for updated
     *     SELECT * FROM order_header WHERE id = 12122 LIMIT 1 FOR UPDATE;
     *     -- End the transaction and release the lock
     *     COMMIT;
     *  }
     */
    @Test
    public void testUpdate_whenDBLock() {
        OrderHeader orderHeader = orderHeaderDao.getById(12_122L);

        Address address = createTestAddress();
        orderHeader.setBillingAddress(address);

        orderHeaderRepository.saveAndFlush(orderHeader);
    }

    @Test
    public void testDeleteById() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        OrderHeader saved = orderHeaderDao.save(orderHeader);

        orderHeaderDao.deleteById(saved.getId());

        assertThrows(EntityNotFoundException.class, () -> orderHeaderDao.getById(saved.getId()));
    }

    @Test
    public void testDeleteCascade() {

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantityOrdered(3);
        orderHeader.addOrderLine(orderLine);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        orderHeader.setOrderApproval(orderApproval);

        orderHeaderRepository.save(orderHeader);

        Long orderApprovalId = orderApproval.getId();

        assertNotNull(orderApprovalId);
        assertEquals(1, orderHeader.getOrderLines().size());

        orderHeaderRepository.deleteById(orderHeader.getId());
        orderHeaderRepository.flush();

        assertThrows(EntityNotFoundException.class, () ->
                orderHeaderDao.getById(orderHeader.getId()));

        assertThrows(EntityNotFoundException.class, () -> {
            OrderApproval proxyOrderApproval = orderApprovalRepository.getReferenceById(orderApproval.getId());
            Hibernate.initialize(proxyOrderApproval);
        });
    }

    @Test
    public void testGetByCustomer() {

        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setCustomer(customer);

        Customer fetchedCustomer = orderHeader.getCustomer();
        orderHeaderRepository.save(orderHeader);

        OrderHeader fetchedOrderHeader = orderHeaderDao.findAllOrderHeaderByCustomer(fetchedCustomer)
                .findFirst().orElse(null);

        assertEquals(customer, fetchedCustomer);

        assertNotNull(fetchedOrderHeader);
        assertEquals(orderHeader.getId(), fetchedOrderHeader.getId());
        assertEquals(customer, fetchedOrderHeader.getCustomer());

        assertNotNull(fetchedOrderHeader.getCustomer().getOrderHeaders());
        assertEquals(1, fetchedOrderHeader.getCustomer().getOrderHeaders().size());

        assertTrue(fetchedCustomer.getOrderHeaders().stream()
                .anyMatch(o -> o.equals(orderHeader)
                )
        );

        assertFalse(fetchedCustomer.getOrderHeaders().contains(orderHeader));

    }

    @Test
    public void testGetByCustomer_assertThat_persistedSet_contains_mayNotWorkProperly() {

        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setCustomer(customer);

        assertTrue(orderHeader.getCustomer().getOrderHeaders().contains(orderHeader));

        orderHeaderRepository.save(orderHeader);
        Customer fetchedCustomer = orderHeader.getCustomer();

        OrderHeader fetchedOrderHeader = orderHeaderDao.findAllOrderHeaderByCustomer(fetchedCustomer)
                .findFirst().orElse(null);

        assertEquals(fetchedOrderHeader, orderHeader);

        assertFalse(fetchedCustomer.getOrderHeaders().contains(orderHeader));
    }

    @Test
    public void testGetByCustomer_whenNotExists_thenThrows() {

        Customer customer = new Customer();
        customer.setCustomerName("Customer#that#is#not#in#db" + RandomString.make(10));
        customerRepository.save(customer);

        assertNull(orderHeaderDao.findAllOrderHeaderByCustomer(customer).findFirst().orElse(null));

    }

    @Test
    @Disabled
    public void testGetByCustomer_countQuantityOrdered() {
        Customer customer = customerRepository
                .findFirstCustomerByCustomerNameIgnoreCase(DataLoadTest.TEST_CUSTOMER)
                .orElseThrow(()->new IllegalStateException("No Test Customer found"));

        IntSummaryStatistics totalQuantityOrdered = orderHeaderDao.findAllOrderHeaderByCustomer(customer)
                .flatMap(orderHeader -> orderHeader.getOrderLines().stream())
                .collect(Collectors.summarizingInt(OrderLine::getQuantityOrdered));

        System.out.println("totalQuantityOrdered.getCount() = " + totalQuantityOrdered.getCount());

    }

    @Test
    public void testGetByCustomer_countQuantityOrdered_oneQuery() {
        Customer customer = customerRepository
                .findFirstCustomerByCustomerNameIgnoreCase(DataLoadTest.TEST_CUSTOMER)
                .orElseThrow(()->new IllegalStateException("No Test Customer found"));

        Long totalQuantityOrdered = orderHeaderRepository.countTotalProductOrderedByCustomerId(customer.getId());

        System.out.println("totalQuantityOrdered.getCount() = " + totalQuantityOrdered);

    }

    private static Address createTestAddress() {
        Address address = new Address();
        address.setAddress("37 West Avenue");
        address.setCity("South Park");
        address.setState("CA");
        address.setZipCode("322233");
        return address;
    }

}