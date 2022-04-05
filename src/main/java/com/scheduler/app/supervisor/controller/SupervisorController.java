package com.scheduler.app.supervisor.controller;

import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.supervisor.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.model.response.SupervisorInfoResponse;
import com.scheduler.app.supervisor.service.SchedulerService;
import com.scheduler.app.utility.service.UtilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;


/**
 * The type - Supervisor controller.
 * Spring Boot Controller for all the APIs for the Supervisor module.
 */
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    /**
     * Auto-wired Component : Scheduler Service.
     */
    @Autowired
    SchedulerService schedulerService;

    /**
     * Auto-wired Component : Utility Service.
     */
    @Autowired
    UtilityService utilityService;

    /**
     * Inputs the Shift details for the upcoming week.
     *
     * @param shiftDetailsRequest the shift details request type object
     * @return the shift details response type object
     */
    @Operation(summary = "Store the shift details of staff for the next week in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provided shift is successfully added" +
                    "to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShiftDetailsResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/save/shifts")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ShiftDetailsResponse inputShiftDetails(@RequestBody ShiftDetailsRequest shiftDetailsRequest) {
        return schedulerService.saveShiftDetails(shiftDetailsRequest);
    }

    /**
     * Algorithm trigger - Triggers to Generate Schedule Algorithm.
     * Had no Input Params or output as the algorithm uses data from the DB & writes the output to the DB as well.
     */
    @GetMapping("/generate/schedule")
    @ResponseStatus(value = HttpStatus.OK)
    public void algorithmTrigger(){
        schedulerService.algoImplementation();
    }

    /**
     * Get statistics for the Supervisor's homepage
     *
     * @param onload     the onload - signifies page load
     * @param department the department to which the Supervisor belongs to.
     * @return the supervisor info response type object
     */
    @Operation(summary = "Retrieve the general information and display it on Supervisor homepage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information like employee count, upcoming holidays etc are " +
                    "successfully retrieved from DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminInfoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @GetMapping("/fetch/info")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    SupervisorInfoResponse getStatistic(@RequestParam Boolean onload, @RequestParam String department){

        return utilityService.getSupervisorStats(onload, department);
    }

}
