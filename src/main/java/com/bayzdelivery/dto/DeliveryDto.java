package com.bayzdelivery.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.bayzdelivery.common.enums.DeliveryStatus;

import lombok.Data;

@Data
public class DeliveryDto {

	private DeliveryStatus status;

	private Instant startTime;

	private Instant endTime;

	private Double distance;

	private BigDecimal commission;
}
