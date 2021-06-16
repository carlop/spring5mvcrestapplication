package es.carlop.api.v1.mapper;

import es.carlop.api.v1.model.CustomerDTO;
import es.carlop.domain.Customer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    @AfterMapping
    default void setUrl(Customer customer, @MappingTarget CustomerDTO customerDTO) {
        customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
    }
}
