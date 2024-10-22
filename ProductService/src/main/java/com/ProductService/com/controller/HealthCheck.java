package com.ProductService.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/ok")
    public String HealthCheck() {
        return "OK";
    }
}
