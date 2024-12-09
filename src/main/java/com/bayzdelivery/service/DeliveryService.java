package com.bayzdelivery.service;

import java.time.Instant;
import java.util.List;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.entity.Courier;
import com.bayzdelivery.model.request.DeliveryStatusUpdateRequest;

public interface DeliveryService {

  public DeliveryDto save(Long orderId, Long courierId);

  public DeliveryDto findById(Long id);

  public DeliveryDto updateStatus(Long id, DeliveryStatusUpdateRequest request);
  
  public List<DeliveryDto> findActiveDeliveriesByCourier(Courier courier);
  
  public List<DeliveryDto> findActiveDeliveriesBetweenInterval(Instant startTime1, Instant startTime2);
}
