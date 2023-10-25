package com.vermeg.dashboard.controller;


import com.vermeg.dashboard.services.KibanaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kibana")
@CrossOrigin("*")
public class KibanaController {

    private final KibanaService kibanaService;

    public KibanaController(KibanaService kibanaService) {
        this.kibanaService = kibanaService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard() {
        System.out.println("hello");
        return kibanaService.getDashboard();
    }

}