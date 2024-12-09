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

import com.bayzdelivery.dto.CourierDto;
import com.bayzdelivery.model.request.CourierRegistrationRequest;
import com.bayzdelivery.service.CourierService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;

@RestController
@RequestMapping("/courier")
public class CourierController {

	@Autowired
	private CourierService courierService;

	@PostMapping
	public CourierDto register(@Valid @RequestBody CourierRegistrationRequest request) {
		return courierService.save(request);
	}

	@GetMapping
	public Page<CourierDto> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") @Max(10) int size) {
		return courierService.findAll(page, size);
	}

	@GetMapping(path = "/{id}")
	public CourierDto findById(@PathVariable(name = "id", required = true) Long id) {
		return courierService.findById(id);
	}
}
