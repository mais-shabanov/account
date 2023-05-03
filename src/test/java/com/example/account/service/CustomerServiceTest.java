package com.example.account.service;

import com.example.account.TestSupport;
import com.example.account.dto.convertor.CustomerDtoConverter;
import com.example.account.exception.CustomerNotFoundException;
import com.example.account.dto.response.CustomerDto;
import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest extends TestSupport {

    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private CustomerDtoConverter customerDtoConverter;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository, customerDtoConverter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = generateCustomer();
        Mockito.when(customerRepository.findById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd")).thenReturn(Optional.of(customer));
        Customer result = customerService.findCustomerById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd");
        assertEquals(result, customer);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("928d7d2c-50d3-485e-b48f")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById("928d7d2c-50d3-485e-b48f"));
    }

    @Test
    public void testGetCustomerId_whenCustomerIdExists_shouldReturnCustomerDto() {
        Customer customer = generateCustomer();
        CustomerDto customerDto = new CustomerDto("928d7d2c-50d3-485e-b48f-6ab076f7dbdd", "", "", Set.of());
        Mockito.when(customerRepository.findById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd")).thenReturn(Optional.of(customer));
        Mockito.when(customerDtoConverter.convertToCustomerDto(customer)).thenReturn(customerDto);
        CustomerDto result = customerService.getCustomerById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd");
        assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById("928d7d2c-50d3-485e-b48f-6ab076f7dbdd"));
        Mockito.verifyNoInteractions(customerDtoConverter);
    }

}
