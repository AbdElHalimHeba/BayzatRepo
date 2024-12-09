package com.bayzdelivery.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
public class AddressDto {

	@NotNull(message = "Longitude can not be null")
	private Double longitude;
	
	@NotNull(message = "Latitude can not be null")
	private Double latitude;
	
	private String street;
	
	private String city;
}
