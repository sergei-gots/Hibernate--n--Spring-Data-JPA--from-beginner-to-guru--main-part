package guru.springframework.beer.services;

import guru.springframework.beer.web.model.CustomerDto;

import java.util.UUID;

/**
 * Created by sergei on 24/05/2025
 */
public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);

    CustomerDto createCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteCustomer(UUID customerId);
}
