package com.learning.microservices.currencyexchangeservice.circuitbreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
	@CircuitBreaker(name = "sample-api", fallbackMethod = "hardCodedResponse")
	public String sampleApi() {
		Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
		logger.info("in sample api method");
		ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/sample-dummy-api", String.class);
		
		return responseEntity.getBody();
	}
	
	public String hardCodedResponse(Exception ex) {
		return "hard coded response from the methhod";
	}
}
