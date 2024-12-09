package com.bayzdelivery.mapping;

import java.math.BigDecimal;
import java.time.Instant;

import com.bayzdelivery.common.enums.DeliveryStatus;
import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.entity.Delivery;
import com.bayzdelivery.entity.Order;

public class DeliveryMapper {

	public static Delivery buildDelivery(Order order, Courier courier, Double distance, BigDecimal commission) {
		Delivery delivery = new Delivery();
		
		delivery.setStartTime(Instant.now());
		delivery.setStatus(DeliveryStatus.ACTIVE);
		delivery.setCommission(commission);
		delivery.setDistance(distance);
		delivery.setCourier(courier);
		delivery.setOrder(order);
		
		return delivery;
	}
	
	public static DeliveryDto deliveryToDeliveryDto(Delivery delivery) {
		DeliveryDto dto = new DeliveryDto();
		
		dto.setCommission(delivery.getCommission());
		dto.setDistance(delivery.getDistance());
		dto.setEndTime(delivery.getEndTime());
		dto.setStartTime(delivery.getStartTime());
		dto.setStatus(delivery.getStatus());
		
		return dto;
	}
}
