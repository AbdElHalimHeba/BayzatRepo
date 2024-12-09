package com.bayzdelivery.util;

import com.bayzdelivery.common.enums.DeliveryStatus;

public class DeliveryUtil {

	public static boolean isEndDeliveryStatus(DeliveryStatus status) {
		return status.equals(DeliveryStatus.COMPLETED);
	}
}
