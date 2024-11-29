package com.learn.microservice.currency_exchange_service.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Circuit breaker for Service A
    /*@Bean
    public CircuitBreaker circuitBreakerForSampleApi() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)  // Open if failure rate > 50%
                .waitDurationInOpenState(Duration.ofMillis(1000))  // Time to wait before retry
                .slidingWindowSize(2)  // Number of calls for calculating failure rate
                .build();
        return CircuitBreaker.of("sample-api", config);
    }*/
}
