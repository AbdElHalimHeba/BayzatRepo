package com.bayzdelivery.service.impl;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bayzdelivery.mapping.CourierReportMapper;
import com.bayzdelivery.model.response.CourierCommissionReportResponse;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.service.CourierReportService;

@Service
public class CourierReportServiceImpl implements CourierReportService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Override
	public Page<CourierCommissionReportResponse> findTopCourierByCommission(Instant startTime, Instant endTime, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Object[]> rows = deliveryRepository.findAllGroupByCourierOrderByCommission(startTime, endTime, pageable);
		
		return new PageImpl<>(
				rows.map(row -> CourierReportMapper.buildCourierCommissionReportResponse(startTime, endTime,
						(Long) row[0], (BigDecimal) row[1], new BigDecimal((Double) row[2]))).toList(),
				pageable, 
				rows.getTotalElements());
		
	}

}
