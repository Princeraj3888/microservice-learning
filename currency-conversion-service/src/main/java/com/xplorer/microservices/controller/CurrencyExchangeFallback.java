package com.xplorer.microservices.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CurrencyExchangeFallback implements CurrencyExchangeProxy{

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeFallback.class);

    @Override
    public CurrencyConversion retrieveExchangeValue(String from, String to) {
        logger.info("Default fallback method is executing ");
        return new CurrencyConversion();
    }
}
