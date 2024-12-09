package com.bayzdelivery.model.request;

import org.springframework.validation.annotation.Validated;

import com.bayzdelivery.dto.AddressDto;

import jakarta.validation.Valid;
import lombok.Data;

@Data 
@Validated
public class CustomerRegistrationRequest extends UserRegistrationRequest {

	private String name;

	@Valid
	private AddressDto address;
}
