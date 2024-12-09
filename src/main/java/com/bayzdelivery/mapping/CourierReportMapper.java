package com.bayzdelivery.mapping;

import java.math.BigDecimal;
import java.time.Instant;

import com.bayzdelivery.model.response.CourierCommissionReportResponse;

public class CourierReportMapper {

	public static CourierCommissionReportResponse buildCourierCommissionReportResponse(Instant startTime, Instant endTime, 
			Long courierId, BigDecimal totalCommission, BigDecimal avgCommission) {
		CourierCommissionReportResponse response = new CourierCommissionReportResponse();
		
		response.setStartTime(startTime);
		response.setEndTime(endTime);
		response.setCourierId(courierId);
		response.setTotalCommission(totalCommission);
		response.setAvgCommission(avgCommission);
		
		return response;
	}
}
