package guru.springframework.orderservice.service;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repository.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sergei on 19/03/2025
 */
@Service
public class OrderDataReadService {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    public void readOrderHeaderById(Long id) {
        OrderHeader orderHeader = orderHeaderRepository.findById(id).get();

        orderHeader.getOrderLines()
                .forEach(ol -> {
                    System.out.println(ol.getProduct().getDescription());
                    ol.getProduct().getCategories().forEach(
                            System.out::println
                    );
                });
    }
}
