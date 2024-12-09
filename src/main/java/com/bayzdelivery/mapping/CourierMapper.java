package com.bayzdelivery.mapping;

import com.bayzdelivery.common.enums.UserType;
import com.bayzdelivery.dto.CourierDto;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.model.request.CourierRegistrationRequest;

public class CourierMapper {

	public static CourierDto courierToCourierDto(Courier courier) {
		
		CourierDto dto = new CourierDto();
		
		dto.setName(courier.getName());
		dto.setRegistrationNumber(courier.getRegistrationNumber());
		
		return dto;
	}
	
//	TODO HIGH - Implement password hashing & GrantedAuthority logic
	public static Courier courierRegistrationToCourier(CourierRegistrationRequest request) {
		
		Courier courier = new Courier();
		
		courier.setUsername(request.getUsername());
		courier.setPassword(request.getPassword());
		courier.setUserType(UserType.COURIER);
		courier.setRole(null);
		courier.setAddress(AddressMapper.addressDtoToAddress(request.getAddress()));
		courier.setName(request.getName());
		courier.setRegistrationNumber(request.getRegistrationNumber());
		
		return courier;
	}

}
