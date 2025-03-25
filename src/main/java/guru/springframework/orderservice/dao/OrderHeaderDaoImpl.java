package guru.springframework.orderservice.dao;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repository.OrderHeaderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.stream.Stream;

/**
 * Created by sergei on 02/03/2025
 */
public class OrderHeaderDaoImpl implements OrderHeaderDao {

    private final OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderDaoImpl(OrderHeaderRepository orderHeaderRepository) {

        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Override
    public OrderHeader save(OrderHeader orderHeader) {

        return orderHeaderRepository.saveAndFlush(orderHeader);
    }

    @Override
    public OrderHeader getById(Long id) {
        return orderHeaderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<OrderHeader> findAll(Pageable pageable) {

        return orderHeaderRepository.findAll(pageable);
    }

    @Override
    public OrderHeader update(OrderHeader orderHeader) {

        return orderHeaderRepository.save(orderHeader);

    }

    @Override
    public void deleteById(Long id) {

        orderHeaderRepository.deleteById(id);
    }

    @Override
    public Stream<OrderHeader> findAllOrderHeaderByCustomer(Customer customer) {

        return orderHeaderRepository.findByCustomer(customer);
    }
}
