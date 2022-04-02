package com.scheduler.app.login.controller;

import com.scheduler.app.login.model.request.LoginRequest;
import com.scheduler.app.login.model.response.LoginResponse;
import com.scheduler.app.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Validate the loginId and Password, and return the type of user to land on " +
            "on the desired landing page.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct user type is returned based on the request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/request")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody LoginResponse inputCredentials(@RequestBody LoginRequest loginRequest){

        return loginService.inputCredentials(loginRequest);
    }


}
