package com.scheduler.app.supervisor.controller;

import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.algorithm.model.response.ShiftDetailsResponse;
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


@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    UtilityService utilityService;

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
    ShiftDetailsResponse shiftDetails(@RequestBody ShiftDetailsRequest shiftDetailsRequest) {
        return schedulerService.saveShiftDetails(shiftDetailsRequest);
    }

//    @GetMapping("/get-schedule")
//    @Produces(value = MediaType.APPLICATION_JSON)
//    public @ResponseBody
//    List<ScheduleDetails> getEmployees(@RequestParam Date startDate, @RequestParam Date endDate) {
//        return  schedulerService.getEmployees(startDate);
//    }

    @GetMapping("/dailyshifts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<DailyShiftPOJO> getShifts(@RequestParam Date shiftDate){
        return schedulerService.getShifts(shiftDate);
    }

    @GetMapping("/generate/schedule")
    @ResponseStatus(value = HttpStatus.OK)
    public void algorithmTrigger(){
        schedulerService.algoImplementation();
    }


    @GetMapping("/emphistory")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getEmpHistory(@RequestParam int employeeId){
        schedulerService.getEmpHistory(employeeId);
        return "success";
    }

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
