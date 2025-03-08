package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.Customer;
import guru.springframework.jdbc.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by sergei on 02/03/2025
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    Optional<OrderHeader> findByCustomer(Customer customer);

}
