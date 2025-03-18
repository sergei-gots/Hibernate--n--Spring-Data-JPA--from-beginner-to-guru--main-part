package guru.springframework.orderservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by sergei on 18/03/2025
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap: I was called!");
    }
}
