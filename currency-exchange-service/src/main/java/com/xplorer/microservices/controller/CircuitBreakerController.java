package com.xplorer.microservices.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sampleApi")
    @Retry(name = "sample-api", fallbackMethod = "hardCodedMethod")
    public String sampleApi(){
        logger.info("from sample api");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-api",
                String.class);

        return forEntity.getBody();
    }

    public String hardCodedMethod(Exception e){
        return "fallback-method";
    }
}
