package com.learn.microservice.limits_service.controller;

import com.learn.microservice.limits_service.configuration.Configuration;
import com.learn.microservice.limits_service.model.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
//        return new Limits(1, 10);
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
