package com.bayzdelivery.service;

import org.springframework.data.domain.Page;

import com.bayzdelivery.dto.CustomerDto;
import com.bayzdelivery.model.request.CustomerRegistrationRequest;

public interface CustomerService {
  public Page<CustomerDto> findAll(int page, int size);

  public CustomerDto save(CustomerRegistrationRequest request);

  public CustomerDto findById(Long id);

}
