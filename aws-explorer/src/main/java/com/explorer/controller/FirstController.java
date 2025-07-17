package com.explorer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/ping")
    public String ping(){
        return "Pong";
    }

}
