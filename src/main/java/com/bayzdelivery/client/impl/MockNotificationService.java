package com.bayzdelivery.client.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bayzdelivery.client.NotificationService;

@Component
public class MockNotificationService implements NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(MockNotificationService.class);
    
	@Async("taskExecutor")
	@Override
    public CompletableFuture<Void> notifyCustomerSupport() {
		LOG.info("Customer support team is notified!");
		return null;
    }

}
