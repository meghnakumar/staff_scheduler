package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
import com.scheduler.app.model.request.ScheduleOutputRequest;
import com.scheduler.app.model.request.ShiftDetailsRequest;
import com.scheduler.app.model.response.ScheduleOutputResponse;
import com.scheduler.app.model.response.ShiftDetailsResponse;

import java.sql.Date;
import java.util.List;

public interface SchedulerService {
    public List<ScheduleDetails> getEmployees(Date date);
    public List<DailyShiftPOJO> getShifts(Date date);
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId);
    public void addEmpHistory(int employeeId);
    public ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest);
    public ScheduleOutputResponse getScheduleByDateTimeDepartment(ScheduleOutputRequest request);
}
