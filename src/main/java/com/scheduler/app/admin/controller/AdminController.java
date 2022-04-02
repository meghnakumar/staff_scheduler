package com.scheduler.app.admin.controller;

import com.scheduler.app.admin.model.request.EmployeeCreationRequest;
import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.supervisor.model.request.ShiftCreationRequest;
import com.scheduler.app.admin.model.response.EmployeeCreationResponse;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.admin.service.EmployeeCreationService;
import com.scheduler.app.admin.service.HolidayCreationService;
import com.scheduler.app.utility.service.UtilityService;
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

/**
 * The type Admin controller.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {



    @Autowired
    EmployeeCreationService employeeCreationService;
    @Autowired
    HolidayCreationService holidayCreationService;

    @Autowired
    UtilityService utilityService;


    @Operation(summary = "Register the employees and store the data to DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee is successfully created and added to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeCreationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/create/employee")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    EmployeeCreationResponse createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest){

        return employeeCreationService.createNewEmployee(employeeCreationRequest);
    }

    @Operation(summary = "Store the holidays declared by admin to holiday table in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Holiday is successfully created and added to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HolidayCreationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/create/holiday")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    HolidayCreationResponse createHoliday(@RequestBody HolidayCreationRequest holiday){

        return holidayCreationService.addNewHoliday(holiday);
    }

    /**
     * Get statistic admin info response.
     *
     * @param onload the onload
     * @return the admin info response
     */
    @Operation(summary = "Retrieve the general information and display it on admin homepage")
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
    AdminInfoResponse getStatistic(@RequestParam Boolean onload){

     return utilityService.getStatistics(onload);
    }

    /**
     * Log shift duration shift creation response.
     *
     * @param shift the shift
     * @return the shift creation response
     */
    @Operation(summary = "Store the shift duration for next week to the DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shift duration is successfully added to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShiftCreationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/shift")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ShiftCreationResponse logShiftDuration(@RequestBody ShiftCreationRequest shift){

        return utilityService.logNewShiftDuration(shift);
    }
}
