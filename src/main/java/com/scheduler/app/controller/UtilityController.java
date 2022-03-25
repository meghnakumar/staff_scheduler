package com.scheduler.app.controller;

import com.scheduler.app.model.response.ShiftTimingsResponse;
import com.scheduler.app.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    UtilityService utilityService;

    @GetMapping("/fetch/shifts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    ShiftTimingsResponse getShiftTimes(){
        return utilityService.getShiftTimes();
    }


}
