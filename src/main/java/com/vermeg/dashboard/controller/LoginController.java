package com.vermeg.dashboard.controller;

import com.vermeg.dashboard.requests.LoginRequest;
import com.vermeg.dashboard.response.LoginResponse;
import com.vermeg.dashboard.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping
    public LoginResponse authenticate(@RequestBody LoginRequest request) {
        return  loginService.authenticate(request);
    }


}
