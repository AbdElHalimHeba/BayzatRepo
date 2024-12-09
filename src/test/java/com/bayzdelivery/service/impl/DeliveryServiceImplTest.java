package com.bayzdelivery.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bayzdelivery.common.enums.DeliveryStatus;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.entity.Delivery;
import com.bayzdelivery.entity.Order;
import com.bayzdelivery.repositories.CourierRepository;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.OrderRepository;
import com.bayzdelivery.service.DeliveryService;

//TODO MEDIUM - Implement rest of TCs
@SpringBootTest
public class DeliveryServiceImplTest {

	@Autowired
	private DeliveryService deliveryService; 
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private CourierRepository courierRepository;
	
	@MockBean
	private DeliveryRepository deliveryRepository;
	
	@Test
	public void save_shouldThrowExceptionWhenCourierHasCurrentDelivery() {
		Order order = buildOrder();
		Courier courier = buildCourier();
		List<Delivery> expected = List.of(buildDelivery());
		
		when(orderRepository.findByIdWithCustomerAndSellerAddress(1l)).thenReturn(Optional.of(order));
		when(courierRepository.findById(1l)).thenReturn(Optional.of(courier));
		when(deliveryRepository.findByCourierAndStatusNot(courier, DeliveryStatus.COMPLETED))
		.thenReturn(expected);
		
		assertThrows(IllegalStateException.class, 
				() -> deliveryService.save(1l, 1l));
	}
	
	private static Order buildOrder() {
		Order order = new Order();
		
		order.setId(1l);
		order.setPrice(BigDecimal.TEN);
		
		return order;
	}
	
	private static Courier buildCourier() {
		Courier courier = new Courier();
		
		courier.setId(1l);
		
		return courier;
	}
	
	private static Delivery buildDelivery() {
		Delivery delivery = new Delivery();
		
		delivery.setId(1l);
		delivery.setStatus(DeliveryStatus.COMPLETED);
		
		return delivery;
	}

}
