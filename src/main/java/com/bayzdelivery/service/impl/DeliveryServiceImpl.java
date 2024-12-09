package com.bayzdelivery.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.bayzdelivery.repositories.CourierRepository;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.OrderRepository;
import com.bayzdelivery.service.DeliveryService;
import com.bayzdelivery.service.strategy.Commission;
import com.bayzdelivery.service.strategy.Distance;
import com.bayzdelivery.util.DeliveryUtil;
import com.bayzdelivery.common.enums.DeliveryStatus;
import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.entity.Delivery;
import com.bayzdelivery.entity.Order;
import com.bayzdelivery.mapping.DeliveryMapper;
import com.bayzdelivery.model.request.DeliveryStatusUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private Distance distance;

	@Autowired
	private Commission commission;

	@Override
	public DeliveryDto save(Long orderId, Long courierId) {
		Order order = orderRepository.findByIdWithCustomerAndSellerAddress(orderId)
				.orElseThrow(() -> new EmptyResultDataAccessException("Order is not found", 1));

		Courier courier = courierRepository.findById(courierId)
				.orElseThrow(() -> new EmptyResultDataAccessException("Courier is not found", 1));

		if(!findActiveDeliveriesByCourier(courier).isEmpty())
			throw new IllegalStateException("Courier can not create multiple deliveries at the same time");

		Double deliveryDistance = distance.calculateDistance(order.getCustomer().getAddress().getLatitude(),
				order.getCustomer().getAddress().getLongitude(), 
				order.getSeller().getAddress().getLatitude(),
				order.getSeller().getAddress().getLongitude());

		BigDecimal deliveryCommission = commission.calculateCommission(order.getPrice(), deliveryDistance);

		Delivery delivery = deliveryRepository.save(DeliveryMapper.buildDelivery(order, courier, deliveryDistance, deliveryCommission));
		
		return DeliveryMapper.deliveryToDeliveryDto(delivery);
	}

	@Override
	public DeliveryDto findById(Long id) {
		Delivery delivery = deliveryRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("Delivery is not found", 1));
		
		return DeliveryMapper.deliveryToDeliveryDto(delivery);
	}

//	TODO HIGH - Verify Courier in Security Context is the same one in Delivery
	@Override
	public DeliveryDto updateStatus(Long id, DeliveryStatusUpdateRequest request) {
		Delivery delivery = deliveryRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("Delivery is not found", 1));

		delivery.setStatus(request.getStatus());

		if (DeliveryUtil.isEndDeliveryStatus(request.getStatus()))
			delivery.setEndTime(Instant.now());

		return DeliveryMapper.deliveryToDeliveryDto(deliveryRepository.save(delivery));
	}

	@Override
	public List<DeliveryDto> findActiveDeliveriesByCourier(Courier courier) {
		return deliveryRepository.findByCourierAndStatusNot(courier, DeliveryStatus.COMPLETED)
		.stream()
		.map(delivery -> DeliveryMapper.deliveryToDeliveryDto(delivery))
		.toList();
		
	}

	@Override
	public List<DeliveryDto> findActiveDeliveriesBetweenInterval(Instant startTime1, Instant startTime2) {
		return deliveryRepository.findByStartTimeBetweenAndStatusNot(startTime1, startTime2, DeliveryStatus.COMPLETED)
		.stream()
		.map(delivery -> DeliveryMapper.deliveryToDeliveryDto(delivery))
		.toList();
	}
	
}
