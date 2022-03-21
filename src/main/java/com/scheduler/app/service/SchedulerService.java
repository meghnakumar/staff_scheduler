package com.scheduler.app.service;

import com.scheduler.app.model.entity.BookDTO;
import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface SchedulerService {
    public Map<String, Map> getEmployees(Date date);
    public List<DailyShiftPOJO> getShifts(Date date);
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId);
    public void addEmpHistory(int employeeId);

    public ScheduleResponse getScheduleByDateTime(ScheduleRequest request);
}
