package guru.springframework.beer.web.controller;

import guru.springframework.beer.services.CustomerService;
import guru.springframework.beer.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * Created by sergei on 24/05/2025
 */
@RequiredArgsConstructor
@RequestMapping("/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomer(@PathVariable("customerId") UUID customerId) {

        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Validated CustomerDto customerDto) {

        CustomerDto savedCustomer = customerService.createCustomer(customerDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedCustomer);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(
            @PathVariable("customerId") UUID customerId,
            @RequestBody @Validated CustomerDto customerDto) {

        customerService.updateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(
            @PathVariable("customerId") UUID customerId
    ) {

        customerService.deleteCustomer(customerId);
    }

}
