package com.infinityloop.aquasentinel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
        return "Hello World";
    }

    @GetMapping("/health")
    public String health(){
        return "HEALTHY AND RUNNING!";
    }
}
