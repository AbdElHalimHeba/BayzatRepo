package com.bayzdelivery.service.strategy;

import java.math.BigDecimal;

public interface Commission {

	public BigDecimal calculateCommission(BigDecimal price, Double distance);
}
