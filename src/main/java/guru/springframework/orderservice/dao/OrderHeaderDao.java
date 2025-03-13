package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sergei on 02/03/2025
 */
public interface OrderHeaderDao {

    OrderHeader save(OrderHeader orderHeader);

    OrderHeader getById(Long id);

    Page<OrderHeader> findAll(Pageable pageable);

    OrderHeader update(OrderHeader orderHeader);

    void deleteById(Long id);

    OrderHeader findOrderHeaderByCustomer(Customer customer);
}
