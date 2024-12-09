package com.bayzdelivery.model.request;

import org.springframework.validation.annotation.Validated;

import com.bayzdelivery.common.enums.DeliveryStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
public class DeliveryStatusUpdateRequest {

	@NotNull(message = "Status can not be null")
	private DeliveryStatus status;
}
