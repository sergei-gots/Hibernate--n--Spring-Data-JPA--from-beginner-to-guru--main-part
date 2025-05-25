package guru.springframework.beer.repositories;

import guru.springframework.beer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by sergei on 11/05/2025
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
