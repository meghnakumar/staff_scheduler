package com.scheduler.app.controller;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.ScheduleDetails;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;
import com.scheduler.app.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/schedule")
public class SupervisorController {

    @Autowired
    SchedulerService schedulerService;

    @GetMapping("/get-schedule")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<ScheduleDetails> getEmployees(@RequestParam Date startDate, @RequestParam Date endDate) {
        return  schedulerService.getEmployees(startDate);
    }

    @GetMapping("/dailyshifts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<DailyShiftPOJO> getShifts(@RequestParam Date shiftDate){
        return schedulerService.getShifts(shiftDate);
    }

    @GetMapping("/emphistory")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getEmpHistory(@RequestParam int employeeId){
        schedulerService.getEmpHistory(employeeId);
        return "success";
    }


    @PostMapping("/fetch/schedule")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ScheduleResponse getScheduleByShift(@RequestBody ScheduleRequest scheduleRequest){

        return schedulerService.getScheduleByDateTime(scheduleRequest);
    }

}
