package com.explorertech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DockerDemoController {

    @GetMapping
    public String applicationStatus(){
        return "Application is up and running!";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name){
        return "Hi "+name+" Welcome to explorer tech AWS ECS Example";
    }
}
