package com.bayzdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean
    ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        int corePoolSize = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(corePoolSize * 2);
        executor.setMaxPoolSize(corePoolSize * 4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-");

        executor.initialize();

        return executor;
    }
}
