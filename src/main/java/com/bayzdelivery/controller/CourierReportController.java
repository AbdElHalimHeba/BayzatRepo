package com.bayzdelivery.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bayzdelivery.model.response.CourierCommissionReportResponse;
import com.bayzdelivery.service.CourierReportService;

import jakarta.validation.constraints.Max;

@RestController
@RequestMapping("/report/courier")
public class CourierReportController {

	@Autowired
	private CourierReportService service;
	
	@GetMapping
	public Page<CourierCommissionReportResponse> findTopCourierByCommission(
			@RequestParam(name = "start-time", required = true) Instant startTime,
			@RequestParam(name = "end-time", required = true) Instant endTime,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") @Max(10) int size) {
		
		return service.findTopCourierByCommission(startTime, endTime, page, size);
	}
}
