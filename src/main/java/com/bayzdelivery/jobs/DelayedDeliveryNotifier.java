package com.bayzdelivery.jobs;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bayzdelivery.client.NotificationService;
import com.bayzdelivery.dto.DeliveryDto;
import com.bayzdelivery.service.DeliveryService;

@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    private static long DELAY = 45l;
    
    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private NotificationService notificationService;
	
    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries() {
    	List<DeliveryDto> deliveries = deliveryService.findActiveDeliveriesBetweenInterval(
    			Instant.now().minus(DELAY, ChronoUnit.MINUTES).minusSeconds(30), 
    			Instant.now().minus(DELAY, ChronoUnit.MINUTES));
    
    	if(!deliveries.isEmpty())
    		notificationService.notifyCustomerSupport().exceptionally(ex -> {
    			LOG.error("An error occured while notifying CS", ex);
    			return null;
    		});
    }

}
