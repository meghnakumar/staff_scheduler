package com.scheduler.app.controller;

import com.scheduler.app.model.request.StaffAvailabilitiesRequest;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import com.scheduler.app.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class SupervisorController {

    @Autowired
    SchedulerService schedulerService;

    @GetMapping("/getSchedule")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getEmployees(@RequestParam Date startDate, @RequestParam Date endDate) {
        schedulerService.getEmployees(startDate);
        return "success";
    }

}
