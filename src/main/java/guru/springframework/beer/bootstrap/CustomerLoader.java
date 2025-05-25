package guru.springframework.beer.bootstrap;

import guru.springframework.beer.domain.Customer;
import guru.springframework.beer.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by sergei on 11/05/2025
 */
@Component
@Slf4j
public class CustomerLoader implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        loadBeerObject();
    }

    private synchronized void loadBeerObject() {

        log.debug("Loading initial customer data. Count is:{}", customerRepository.count());

        if (customerRepository.count() > 0) {
            log.debug("Customer Records are already presented in the database");
            return;
        }

        customerRepository.save(buildCustomer("Michael", "Westen"));
        customerRepository.save(buildCustomer("Fionna", "Glenanne"));
        customerRepository.save(buildCustomer("Jesse", "Porter"));
        customerRepository.save(buildCustomer("Madeline", "Westen"));
        customerRepository.save(buildCustomer("Sam", "Axe"));

        log.debug("Beer Records loaded: {}", customerRepository.count());
    }

    private Customer buildCustomer(String firstname, String lastname) {

        return Customer.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }
}
