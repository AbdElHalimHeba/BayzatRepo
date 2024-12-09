package com.bayzdelivery.client;

import java.util.concurrent.CompletableFuture;

public interface NotificationService {

	public CompletableFuture<Void> notifyCustomerSupport();
}
