package com.xplorer.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}")
    public CurrencyConversion calculateCurrencyService(@PathVariable String from, @PathVariable String to){
        return new CurrencyConversion(100L, from, to, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TWO, "test" );
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyServiceFeign(@PathVariable String from, @PathVariable String to, @PathVariable Long quantity){

        CurrencyConversion conversion =  currencyExchangeProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(100L, from, to, BigDecimal.valueOf(quantity),
                conversion.getConversionMultiple(), conversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)), "test - feign" );
    }
}
