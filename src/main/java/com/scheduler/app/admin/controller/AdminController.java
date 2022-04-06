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
 * The type - Admin Controller.
 * Spring Boot Controller for all the APIs for the Admin module.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    /**
     * Auto-wired Component : Employee Creation Service.
     */
    @Autowired
    EmployeeCreationService employeeCreationService;

    /**
     * Auto-wired Component : Holiday Creation Service
     */
    @Autowired
    HolidayCreationService holidayCreationService;

    /**
     * Auto-wired Component : Utility Service
     */
    @Autowired
    UtilityService utilityService;


    /**
     * Create employee method associated with the "/admin/create/employee" API call.
     * Creates a new employee in the system.
     *
     * @param employeeCreationRequest the employee creation request type object
     * @return the employee creation response type object
     */
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

    /**
     * Create holiday method associated with the "/admin/create/holiday" API call.
     * Creates a new holiday in the system.
     *
     * @param holiday the holiday creation request type object.
     * @return the holiday creation response type object.
     */
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
     * Get Statistic method associated with the "/admin/fetch/info" API call.
     * Returns information for the Admin Homepage.
     *
     * @param onload the onload boolean value signifying page load.
     * @return the admin info response type object.
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
     * Log shift duration method associated with the "/admin/shift" API call.
     * Updates the value of the shift duration for every shift in the company.
     *
     * @param shift the shift creating request type object
     * @return the shift creation response type object
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
