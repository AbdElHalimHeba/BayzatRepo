package com.bayzdelivery.service.strategy.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bayzdelivery.service.strategy.Commission;

@Service
public class DefaultCommission implements Commission {

	private static final Double PRICE_PERCENTAGE = 0.05;
	private static final Double DISTANCE_MARGIN = 0.5;
	
	@Override
	public BigDecimal calculateCommission(BigDecimal price, Double distance) {
		return price.multiply(new BigDecimal(PRICE_PERCENTAGE))
		.add(new BigDecimal(distance * DISTANCE_MARGIN));
	}

}
