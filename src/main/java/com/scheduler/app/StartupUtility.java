package com.scheduler.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Startup Utility class has APIs that are loaded during the Application invocation
 * to provide information about the Application's context.
 */
@RestController
public class StartupUtility implements ErrorController {

    @Value("${spring.version}")
    private String version;

    /**
     * Retuns the Version value - which depicts which Database the Application currently pointing to.
     *
     * @return the string
     */
    @GetMapping("/version")
    public String version() {

        return  version;
    }


    /**
     * Error API for redirecting the failed Calls.
     *
     * @return the string
     */
    @RequestMapping("/error")
    public String error(){

        return "<h2> ERROR OCCURRED !!! </h2> " + HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

}
