package com.scheduler.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StartupUtility implements ErrorController {

    @Value("${spring.version}")
    private String version;

    @GetMapping("/version")
    public String version() {

        return  version;
    }


    @RequestMapping("/error")
    public String error(){

        return "<h2> ERROR OCCURRED !!! </h2> " + HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

}
