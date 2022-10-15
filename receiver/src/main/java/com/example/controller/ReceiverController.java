package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {

    @GetMapping
    public ResponseEntity<String> getHealthCheck(){
        return ResponseEntity.ok("{\"status\":\"OK\"}");
    }

}
