package com.bayzdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bayzdelivery.dto.CustomerDto;
import com.bayzdelivery.model.request.CustomerRegistrationRequest;
import com.bayzdelivery.service.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping
  public void register(@Valid @RequestBody CustomerRegistrationRequest request) {
    customerService.save(request);
  }

  @GetMapping
  public Page<CustomerDto> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") @Max(10) int size) {
    return customerService.findAll(page, size);
  }

  @GetMapping(path = "/{id}")
  public CustomerDto findById(@PathVariable(name="id", required = true) Long id) {
    return customerService.findById(id);
  }

}
