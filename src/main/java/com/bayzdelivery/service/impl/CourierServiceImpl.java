package com.bayzdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bayzdelivery.dto.CourierDto;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.mapping.CourierMapper;
import com.bayzdelivery.model.request.CourierRegistrationRequest;
import com.bayzdelivery.repositories.CourierRepository;
import com.bayzdelivery.service.CourierService;
import com.bayzdelivery.service.UserService;

@Service
public class CourierServiceImpl implements CourierService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourierRepository courierRepository;
	
	@Override
	public CourierDto save(CourierRegistrationRequest request) {
		if(userService.existsByUsername(request.getUsername()))
			throw new IllegalArgumentException("User already exists");
		
		Courier courier = courierRepository.save(CourierMapper.courierRegistrationToCourier(request));
		return CourierMapper.courierToCourierDto(courier);
	}

	@Override
	public Page<CourierDto> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Courier> couriers = courierRepository.findAll(pageable);
		
		return new PageImpl<>(
				couriers.map(courier -> 
					CourierMapper.courierToCourierDto(courier))
					.toList(), 
				pageable,
				couriers.getTotalElements());
	}

	@Override
	public CourierDto findById(Long id) {
		Courier courier = courierRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("Courier is not found", 1));
		
		return CourierMapper.courierToCourierDto(courier);
	}

}
