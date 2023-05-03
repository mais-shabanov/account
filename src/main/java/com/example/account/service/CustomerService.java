package com.example.account.service;

import com.example.account.dto.convertor.CustomerDtoConverter;
import com.example.account.exception.CustomerNotFoundException;
import com.example.account.dto.response.CustomerDto;
import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConvertor;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerDtoConverter customerDtoConvertor
    ) {
        this.customerRepository = customerRepository;
        this.customerDtoConvertor = customerDtoConvertor;
    }

    public Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id - " + id));
    }

    public CustomerDto getCustomerById(String id) {
        return customerDtoConvertor.convertToCustomerDto(findCustomerById(id));
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoConvertor::convertToCustomerDto)
                .collect(Collectors.toList());
    }
}
