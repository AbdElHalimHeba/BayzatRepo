package com.bayzdelivery.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bayzdelivery.model.request.CourierRegistrationRequest;
import com.bayzdelivery.service.CourierService;
import com.bayzdelivery.service.UserService;

//TODO MEDIUM - Implement rest of TCs
@SpringBootTest
public class CourierServiceImplTest {

	@Autowired
	private CourierService courierService;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void save_shouldThrowExceptionWhenUsernameExists() {
		CourierRegistrationRequest request = buildRequest();
				
		when(userService.existsByUsername(request.getUsername())).thenReturn(true);
		
		assertThrows(IllegalArgumentException.class, 
				() -> courierService.save(request));
	}
	
	private static CourierRegistrationRequest buildRequest() {
		CourierRegistrationRequest request = new CourierRegistrationRequest();
		
		request.setUsername("customer@gmail.com");
		
		return request;
	}
}
