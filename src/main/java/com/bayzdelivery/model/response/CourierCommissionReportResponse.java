package com.bayzdelivery.model.response;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Data;

@Data
public class CourierCommissionReportResponse {

	private Instant startTime;

	private Instant endTime;

	private BigDecimal totalCommission;
	
	private BigDecimal avgCommission;
	
	private Long courierId;
}
