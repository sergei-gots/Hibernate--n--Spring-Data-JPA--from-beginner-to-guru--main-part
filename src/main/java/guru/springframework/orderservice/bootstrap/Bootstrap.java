package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.service.OrderDataReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 18/03/2025
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderDataReadService orderHeaderReadService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrap: I was called!");

        orderHeaderReadService.readOrderHeaderById(12122L);
    }

}
