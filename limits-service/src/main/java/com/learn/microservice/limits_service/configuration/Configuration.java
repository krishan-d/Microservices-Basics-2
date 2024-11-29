package com.learn.microservice.limits_service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
@Data // provides getter & setter methods
public class Configuration {

    private int minimum;
    private int maximum;

}
