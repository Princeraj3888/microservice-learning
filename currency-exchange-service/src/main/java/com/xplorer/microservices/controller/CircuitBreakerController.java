package com.xplorer.microservices.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    //@Retry(name = "sample-api", fallbackMethod = "hardCodedMethod")
    //@CircuitBreaker(name ="sample-api", fallbackMethod = "hardCodedMethod")
    //@RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    public String sampleApi(){
        logger.info("from sample api");
        /*ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-api",
                String.class);

        return forEntity.getBody();*/

        return "sample api";
    }

    public String hardCodedMethod(Exception e){
        return "fallback-method";
    }

    @GetMapping("/sampleBulkheadDemo")
    @Bulkhead(name = "sampleBulkheadDemo", type = Bulkhead.Type.SEMAPHORE)
    public String processRequest() throws InterruptedException {
        System.out.println("processing request in: "+ Thread.currentThread().getName());
        Thread.sleep(3000);
        return "processed by"+Thread.currentThread().getName();
    }
}
