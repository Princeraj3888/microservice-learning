package com.learning.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.learning.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.learning.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment; 
	
	@Autowired
	private CurrencyExchangeRepository respository;
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveCurrecnyExchange(@PathVariable String from, @PathVariable String to) {
		//CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		
		logger.info("retrieveCurrecnyExchange service called with from {} to {}", from, to);
		
		CurrencyExchange currencyExchange = respository.findByFromAndTo(from, to);
		
		if(currencyExchange==null)
			throw new RuntimeException("Unable to find from: "+from+" to: "+to);
		
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}

}
