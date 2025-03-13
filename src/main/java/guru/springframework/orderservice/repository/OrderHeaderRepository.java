package guru.springframework.orderservice.repository;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sergei on 02/03/2025
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    Optional<OrderHeader> findByCustomer(Customer customer);

}
