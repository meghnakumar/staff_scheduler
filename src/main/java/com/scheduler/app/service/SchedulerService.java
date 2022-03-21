package com.scheduler.app.service;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;

import java.sql.Date;
import java.util.List;

public interface SchedulerService {

    public List<EmpAvailabilityPOJO> getEmployees(Date date);

    public ScheduleResponse getScheduleByDateTime(ScheduleRequest request);
}
