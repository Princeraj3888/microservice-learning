package com.xplorer.microservices.limits_service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Configuration {
    @Value("${limits-service.minimum}")
    private int minimum;

    @Value("${limits-service.maximum}")
    private int maximum;
}
