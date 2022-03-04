package com.scheduler.app.controller;

import com.scheduler.app.model.request.StaffAvailabilitiesRequest;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import com.scheduler.app.service.StaffAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

        @Autowired
        StaffAvailabilityService staffAvailabilityService;


        @PostMapping("/input/availability")
        @Consumes(value = MediaType.APPLICATION_JSON)
        @Produces(value = MediaType.APPLICATION_JSON)
        public @ResponseBody
        StaffAvailabilityResponse inputStaffAvailability(@RequestBody StaffAvailabilitiesRequest  staffAvailabilitiesRequest){

            List<StaffAvailabilityRequest> staffAvailibilityRequest = staffAvailabilitiesRequest.getStaffAvailabilityRequest();
            return staffAvailabilityService.inputStaffAvailability(staffAvailibilityRequest);
    }

}
