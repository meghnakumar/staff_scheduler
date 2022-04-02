package com.scheduler.app.utility.controller;

import com.scheduler.app.supervisor.model.response.ShiftTimingsResponse;
import com.scheduler.app.utility.service.UtilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    UtilityService utilityService;

    @Operation(summary = "Fetches the shifts set by admin for the most recent date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shifts for the most recent date are" +
                    "are successfully retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShiftTimingsResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @GetMapping("/fetch/shifts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ShiftTimingsResponse getShiftTimes(){
        return utilityService.getShiftTimes();
    }


}
