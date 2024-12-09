package com.bayzdelivery.model.request;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
public class UserRegistrationRequest {

	@NotNull(message = "Email can not be null")
	@Email(message = "Email must be valid")
	private String username;
	
	@NotNull(message = "Password can not be null")
	private String password;
}
