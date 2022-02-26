package com.scheduler.app.controller;

import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;
import com.scheduler.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/request")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody LoginResponse inputCredentials(@RequestBody LoginRequest loginRequest){

        return loginService.inputCredentials(loginRequest);
    }


}
