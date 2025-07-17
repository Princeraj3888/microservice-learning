package com.xplorer.microservices.limits_service.controller;

import com.xplorer.microservices.limits_service.config.Configuration;
import com.xplorer.microservices.limits_service.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits getLimitsDetails(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
