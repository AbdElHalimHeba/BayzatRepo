package com.bayzdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bayzdelivery.dto.CustomerDto;
import com.bayzdelivery.entity.Customer;
import com.bayzdelivery.mapping.CustomerMapper;
import com.bayzdelivery.model.request.CustomerRegistrationRequest;
import com.bayzdelivery.repositories.CustomerRepository;
import com.bayzdelivery.service.CustomerService;
import com.bayzdelivery.service.UserService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UserService userService;
	
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<CustomerDto> findAll(int page, int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Customer> customers = customerRepository.findAll(pageable);
		
		return new PageImpl<>(
				customers.map(customer -> 
					CustomerMapper.customerToCustomerDto(customer))
					.toList(), 
				pageable,
				customers.getTotalElements());
    }

    public CustomerDto save(CustomerRegistrationRequest request) {
    	if(userService.existsByUsername(request.getUsername()))
			throw new IllegalArgumentException("User already exists");
		
    	Customer customer = customerRepository.save(CustomerMapper.customerRegistrationToCustomer(request));
		return CustomerMapper.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto findById(Long id) {
    	Customer customer = customerRepository.findById(id)
    			.orElseThrow(() -> new EmptyResultDataAccessException("Customer is not found", 1));
		
		return CustomerMapper.customerToCustomerDto(customer);
    }
}
