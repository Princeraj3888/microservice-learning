package com.xplorer.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        return currencyExchangeRepository.findByFromAndTo(from, to);
        //CurrencyExchange(100L, from, to, BigDecimal.valueOf(85), environment.getProperty("local.server.port"));

    }

    @GetMapping("/currency-exchange/findAll")
    public List<CurrencyExchange> retrieveAllCurrencyExchange(){
        return currencyExchangeRepository.findAll();
    }
}
