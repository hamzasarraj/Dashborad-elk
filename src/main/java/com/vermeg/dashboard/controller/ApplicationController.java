package com.vermeg.dashboard.controller;

import com.vermeg.dashboard.entities.Application;
import com.vermeg.dashboard.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Application> findAll() {
        return applicationService.findAll();
    }
}
