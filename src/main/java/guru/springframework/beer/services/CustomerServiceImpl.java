package guru.springframework.beer.services;

import guru.springframework.beer.domain.Customer;
import guru.springframework.beer.repositories.CustomerRepository;
import guru.springframework.beer.web.mappers.CustomerMapper;
import guru.springframework.beer.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by sergei on 24/05/2025
 */
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        Customer customer = customerRepository.findByIdOrElseThrowNotFoundException(customerId);
        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customerToSave = customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {

        Customer customerToUpdate = customerRepository.findByIdOrElseThrowNotFoundException(customerId);
        customerMapper.updateCustomer(customerToUpdate, customerDto);
        customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(UUID customerId) {

        Customer customerToDelete = customerRepository.findByIdOrElseThrowNotFoundException(customerId);
        customerRepository.delete(customerToDelete);
    }

}
