package com.scheduler.app.staff.controller;

import com.scheduler.app.staff.model.request.EmployeeAvailabilityExistsRequest;
import com.scheduler.app.staff.model.response.EmployeeAvailabilityExistsResponse;
import com.scheduler.app.staff.model.request.StaffAvailabilitiesRequest;
import com.scheduler.app.staff.model.request.StaffAvailabilityRequest;
import com.scheduler.app.staff.model.response.EmployeeDetailsResponse;
import com.scheduler.app.staff.model.response.StaffAvailabilityResponse;
import com.scheduler.app.staff.service.StaffAvailabilityService;
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
import java.util.List;

/**
 * The type - Staff controller.
 * Spring Boot Controller for all the APIs for the Staff module.
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    /**
     * Auto-wired Component : Staff Availability Service.
     */
    @Autowired
        StaffAvailabilityService staffAvailabilityService;

    /**
     * Inputs the staff availability for each employee as provided by them in the front-end.
     *
     * @param staffAvailabilitiesRequest the staff availabilities request type object
     * @return the staff availability response type object
     */
    @Operation(summary = "Store the availability of staff for the next week in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provided staff availability is successfully added to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StaffAvailabilityResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
        @PostMapping("/input/availability")
        @Consumes(value = MediaType.APPLICATION_JSON)
        @Produces(value = MediaType.APPLICATION_JSON)
        public @ResponseBody
        StaffAvailabilityResponse inputStaffAvailability(@RequestBody StaffAvailabilitiesRequest  staffAvailabilitiesRequest){

            List<StaffAvailabilityRequest> staffAvailibilityRequest = staffAvailabilitiesRequest.getStaffAvailabilityRequestList();
            return staffAvailabilityService.inputStaffAvailability(staffAvailibilityRequest);
    }

    /**
     * Get employee information so it can be displayed on the Staff Homepage.
     *
     * @param employeeNumber the employee number
     * @return the employee details response type object
     */
    @Operation(summary = "Fetch the Employee Details to be displayed on the Employee Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully fetched employee details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDetailsResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @GetMapping(value = "get/info")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    EmployeeDetailsResponse getEmployeeInformation(@RequestParam String  employeeNumber){
        return staffAvailabilityService.fetchEmployeeInfo(employeeNumber);
    }

    /**
     * Checks if the employee availability already exists or not. If it does, returns the object of the found match.
     *
     * @param request the request
     * @return the employee availability exists response
     */
    @Operation(summary = "Fetch if employee availability already exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sucessfully fetched employee availability details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeAvailabilityExistsResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping(value = "check/availability")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    EmployeeAvailabilityExistsResponse getEmployeeAvailability(@RequestBody EmployeeAvailabilityExistsRequest request){
        return staffAvailabilityService.checkEmployeeAvailability(request);
    }
}
