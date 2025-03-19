package guru.springframework.orderservice.service;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sergei on 20/03/2025
 */
@Service
public class CustomerOptimisticLockingDemoService  {

    @Autowired
    CustomerRepository customerRepository;

    public void optimisticLockingDemo() {

        Customer customer = new Customer();
        customer.setCustomerName("Test Customer's Version");
        Customer savedCustomer = customerRepository.save(customer);

        System.out.println("version is:" + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomerVersion2 = customerRepository.save(savedCustomer);
        System.out.println("customer.version is:" + customer.getVersion());
        System.out.println("savedCustomer.version is:" + savedCustomer.getVersion());
        System.out.println("savedCustomerVersion2.version is:" + savedCustomerVersion2.getVersion());

        savedCustomerVersion2.setCustomerName("Testing Version 3");
        Customer savedCustomerVersion3 = customerRepository.save(savedCustomerVersion2);
        System.out.println("customer.version is:" + customer.getVersion());
        System.out.println("savedCustomer.version is:" + savedCustomer.getVersion());
        System.out.println("savedCustomerVersion2.version is:" + savedCustomerVersion2.getVersion());
        System.out.println("savedCustomerVersion3.version is:" + savedCustomerVersion3.getVersion());

        System.out.println("customer.customerName is:" + customer.getCustomerName());
        System.out.println("savedCustomer.customerName is:" + savedCustomer.getCustomerName());
        System.out.println("savedCustomerVersion2.customerName is:" + savedCustomerVersion2.getCustomerName());
        System.out.println("savedCustomerVersion3.customerName is:" + savedCustomerVersion3.getCustomerName());

        Customer savedCustomerVersion4 = customerRepository.save(savedCustomerVersion3);
        System.out.println("savedCustomerVersion4.version is:" + savedCustomerVersion4.getVersion());


        customerRepository.delete(savedCustomerVersion4);
    }

}
