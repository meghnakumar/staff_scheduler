package com.scheduler.app.controller;

import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;
import com.scheduler.app.service.SchedulerService;
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

    @PostMapping("/fetch")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ScheduleResponse getScheduleByShift(@RequestBody ScheduleRequest scheduleRequest){

        return schedulerService.getScheduleByDateTime(scheduleRequest);
    }
}
