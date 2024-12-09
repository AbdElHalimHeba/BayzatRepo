package com.bayzdelivery.mapping;

import com.bayzdelivery.dto.AddressDto;
import com.bayzdelivery.entity.Address;

public class AddressMapper {

	public static AddressDto addressToAddressDto(Address address) {
		AddressDto dto = new AddressDto();
		
		dto.setCity(address.getCity());
		dto.setLatitude(address.getLatitude());
		dto.setLongitude(address.getLongitude());
		dto.setStreet(address.getStreet());
		
		return dto;
	}
	
	public static Address addressDtoToAddress(AddressDto dto) {
		Address address = new Address();
		
		address.setCity(dto.getCity());
		address.setLatitude(dto.getLatitude());
		address.setLongitude(dto.getLongitude());
		address.setStreet(dto.getStreet());
		
		return address;
	}
}
