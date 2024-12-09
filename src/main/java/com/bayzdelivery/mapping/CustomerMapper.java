package com.bayzdelivery.mapping;

import com.bayzdelivery.common.enums.UserType;
import com.bayzdelivery.dto.CustomerDto;
import com.bayzdelivery.entity.Customer;
import com.bayzdelivery.model.request.CustomerRegistrationRequest;

public class CustomerMapper {

	public static CustomerDto customerToCustomerDto(Customer customer) {
		
		CustomerDto dto = new CustomerDto();
		
		dto.setName(customer.getName());
		
		return dto;
	}
	
//	TODO HIGH - Implement password hashing & GrantedAuthority logic
	public static Customer customerRegistrationToCustomer(CustomerRegistrationRequest request) {
		
		Customer customer = new Customer();
		
		customer.setUsername(request.getUsername());
		customer.setPassword(request.getPassword());
		customer.setUserType(UserType.CUSTOMER);
		customer.setRole(null);
		customer.setAddress(AddressMapper.addressDtoToAddress(request.getAddress()));
		customer.setName(request.getName());
		
		return customer;
	}

}
