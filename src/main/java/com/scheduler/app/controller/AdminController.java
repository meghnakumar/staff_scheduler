package com.scheduler.app.controller;

import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;
import com.scheduler.app.model.response.InfoResponse;
import com.scheduler.app.service.EmployeeCreationService;
import com.scheduler.app.service.HolidayCreationService;
import com.scheduler.app.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/admin")
public class AdminController {



    @Autowired
    EmployeeCreationService employeeCreationService;
    @Autowired
    HolidayCreationService holidayCreationService;

    @Autowired
    UtilityService utilityService;


    @PostMapping("/create/employee")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    EmployeeCreationResponse createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest){

        return employeeCreationService.createNewEmployee(employeeCreationRequest);
    }

    @PostMapping("/create/holiday")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    HolidayCreationResponse createHoliday(@RequestBody HolidayCreationRequest holiday){

        return holidayCreationService.addNewHoliday(holiday);
    }

    @GetMapping("/fetch/info")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    InfoResponse getStatistic(@RequestParam Boolean onload){

     return utilityService.getStatistics(onload);
    }
}
