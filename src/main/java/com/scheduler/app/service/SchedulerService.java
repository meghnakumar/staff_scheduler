package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface SchedulerService {
    public List<ScheduleDetails> getEmployees(Date date);
    public List<DailyShiftPOJO> getShifts(Date date);
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId);
    public void addEmpHistory(int employeeId);

    public ScheduleResponse getScheduleByDateTime(ScheduleRequest request);
}
