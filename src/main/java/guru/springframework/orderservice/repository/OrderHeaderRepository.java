package guru.springframework.orderservice.repository;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * Created by sergei on 02/03/2025
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    Stream<OrderHeader> findByCustomer(Customer customer);

    @Query(nativeQuery = true,
    value = """
            SELECT SUM(quantity_ordered) FROM order_line
            WHERE order_header_id IN (
                SELECT id FROM order_header WHERE customer_id = :customerId
            )
            """)
    Long countTotalProductOrderedByCustomerId(Long customerId);
}
