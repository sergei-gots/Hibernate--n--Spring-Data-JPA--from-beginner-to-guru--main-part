package guru.springframework.orderservice.repository;

import guru.springframework.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 09/03/2025
 */
public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
