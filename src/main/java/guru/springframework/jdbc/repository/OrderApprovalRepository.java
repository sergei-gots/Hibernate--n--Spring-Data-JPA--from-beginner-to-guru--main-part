package guru.springframework.jdbc.repository;

import guru.springframework.jdbc.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sergei on 09/03/2025
 */
public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
