package com.scheduler.app.controller;


import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.Time;


@RestController
@RequestMapping("/schedule")
public class SupervisorController {

    @Autowired
    SchedulerService schedulerService;

    @GetMapping("/get-schedule")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<EmpAvailabilityPOJO> getEmployees(@RequestParam Date startDate, @RequestParam Date endDate) {
        return  schedulerService.getEmployees(startDate);
    }

    @GetMapping("/dailyshifts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getShifts(@RequestParam Date shiftDate, @RequestParam Time startTime, @RequestParam Time endTime){
        schedulerService.getShifts(shiftDate,startTime,endTime);
        return "success";
    }

    @GetMapping("/emphistory")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getEmpHistory(@RequestParam int employeeId){
        schedulerService.getEmpHistory(employeeId);
        return "success";
    }




}
