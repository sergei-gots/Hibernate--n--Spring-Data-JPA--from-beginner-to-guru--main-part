package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repository.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sergei on 18/03/2025
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrap: I was called!");

        OrderHeader orderHeader = orderHeaderRepository.findById(12122L).get();

        orderHeader.getOrderLines()
                .forEach(ol -> {
                    System.out.println(ol.getProduct().getDescription());
                    ol.getProduct().getCategories().forEach(
                            System.out::println
                    );
                });
    }
}
