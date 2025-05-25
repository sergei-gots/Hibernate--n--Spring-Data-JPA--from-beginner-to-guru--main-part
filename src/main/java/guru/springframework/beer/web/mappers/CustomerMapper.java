package guru.springframework.beer.web.mappers;

import guru.springframework.beer.domain.Customer;
import guru.springframework.beer.web.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Created by sergei on 25/05/2025
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    void updateCustomer(@MappingTarget Customer customerToUpdate, CustomerDto customerDto);
}
