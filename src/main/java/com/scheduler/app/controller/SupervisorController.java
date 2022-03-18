package com.scheduler.app.controller;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;

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

}
