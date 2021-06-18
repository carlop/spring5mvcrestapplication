package es.carlop.controllers.v1;

import es.carlop.BaseURLs;
import es.carlop.api.v1.model.CustomerDTO;
import es.carlop.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static es.carlop.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FC_FIRST_NAME = "Jimmy";
    public static final String FC_LAST_NAME = "McGill";

    public static final String SC_FIRST_NAME = "Kim";
    public static final String SC_LAST_NAME = "Wexler";

    public static final String CUSTOMER_1_API_URI = BaseURLs.CUSTOMERS_URL + "1";


    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FC_FIRST_NAME);
        customer1.setLastName(FC_LAST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName(SC_FIRST_NAME);
        customer2.setLastName(SC_LAST_NAME);

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(BaseURLs.CUSTOMERS_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(FC_FIRST_NAME);
        customer.setLastName(FC_LAST_NAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get(CUSTOMER_1_API_URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FC_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(FC_LAST_NAME)));

    }

    @Test
    public void testCreateNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FC_FIRST_NAME);
        customerDTO.setLastName(FC_LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl(CUSTOMER_1_API_URI);

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post(BaseURLs.CUSTOMERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.firstName", equalTo(FC_FIRST_NAME)))
            .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_1_API_URI)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FC_FIRST_NAME);
        customerDTO.setLastName(FC_LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl(CUSTOMER_1_API_URI);

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put(CUSTOMER_1_API_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FC_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(FC_LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_1_API_URI)));
    }

    @Test
    public void testPatchCustomer() throws Exception {

        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(FC_FIRST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(SC_LAST_NAME);
        returnDTO.setCustomerUrl(CUSTOMER_1_API_URI);

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(CUSTOMER_1_API_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FC_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(SC_LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_1_API_URI)));

    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CUSTOMER_1_API_URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

}
