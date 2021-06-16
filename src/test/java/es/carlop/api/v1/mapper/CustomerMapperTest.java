package es.carlop.api.v1.mapper;

import es.carlop.api.v1.model.CustomerDTO;
import es.carlop.domain.Customer;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Jimmy";
    public static final String LAST_NAME = "McGill";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDTO() throws Exception {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }

}
