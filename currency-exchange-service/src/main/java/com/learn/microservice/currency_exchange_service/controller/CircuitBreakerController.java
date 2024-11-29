package com.learn.microservice.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "fallbackForServiceA")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackForServiceA")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackForServiceA") // To break chain after certain condition
//    @RateLimiter(name = "default") // Limits no of requests in a time window
//    @Bulkhead(name="sample-api") // To configure no of concurrent requests
    public String sampleAPi() {

        logger.info("sample api call received!");
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8000/sample-dummy", String.class);
        return response.getBody();
//        return "Sample API";

    }

    // Fallback for Service A
    public String fallbackForServiceA(Throwable throwable) {
        return "Fallback: Service A is down!";
    }


}
