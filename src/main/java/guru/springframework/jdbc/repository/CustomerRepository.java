package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 07/03/2025
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
