package com.bayzdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.model.request.DeliveryStatusUpdateRequest;
import com.bayzdelivery.service.DeliveryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

  @Autowired
  private DeliveryService deliveryService;

  @PostMapping
  public DeliveryDto create(@RequestParam(name = "order-id", required = true) Long orderId, 
		  @RequestParam(name = "courier-id", required = true) Long courierId) {
    return deliveryService.save(orderId, courierId);
  }
  
  @PatchMapping(path = "/{id}/status")
  public DeliveryDto updateStatus(@PathVariable(name="id", required = true) Long id, 
		  @Valid @RequestBody DeliveryStatusUpdateRequest request) {
    return deliveryService.updateStatus(id, request);
  }

  @GetMapping(path = "/{id}")
  public DeliveryDto findById(@PathVariable(name="id", required = true) Long id) {
    return deliveryService.findById(id);
  }
  
}
