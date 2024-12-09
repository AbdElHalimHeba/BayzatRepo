package com.bayzdelivery.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("within(com.bayzdelivery.service..*)")
	public void allServices() {}
	
	@AfterThrowing(value = "allServices()", throwing = "exception", argNames = "joinPoint, exception")
    public void logServiceAfterThrow(JoinPoint joinPoint, Object exception) {
    	
		LOGGER.info("Service " + joinPoint.getSignature().getName() + " thrown " + exception);
        
    }
	
}
