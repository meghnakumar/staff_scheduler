package com.scheduler.app.controller;

import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;
import com.scheduler.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/request")

    public LoginResponse inputCredentials(@RequestBody LoginRequest loginRequest){

        return loginService.inputCredentials(loginRequest);
    }


}
