package com.scheduler.app.algorithm.controller;

import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.supervisor.service.SchedulerService;
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
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    SchedulerService schedulerService;

    @Operation(summary = "Return the Generated Schedule based on the requested Shift Date, Shift Time, and Department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shift details are successfully queried from the DB and returned ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShiftCreationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content)})
    @PostMapping("/fetch")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ScheduleOutputResponse getScheduleByShift(@RequestBody ScheduleOutputRequest scheduleOutputRequest){

        return schedulerService.getScheduleByDateTimeDepartment(scheduleOutputRequest);
    }
}
