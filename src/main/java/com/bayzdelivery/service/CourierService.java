package com.bayzdelivery.service;

import org.springframework.data.domain.Page;

import com.bayzdelivery.dto.CourierDto;
import com.bayzdelivery.model.request.CourierRegistrationRequest;

public interface CourierService {

	public CourierDto save(CourierRegistrationRequest request);

	public Page<CourierDto> findAll(int page, int size);

	public CourierDto findById(Long id);

}
