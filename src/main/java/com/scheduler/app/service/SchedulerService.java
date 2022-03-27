package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.request.ShiftDetailsRequest;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.ScheduleResponse;
import com.scheduler.app.model.response.ShiftDetailsResponse;
import com.scheduler.app.model.response.StaffAvailabilityResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface SchedulerService {
    public List<ScheduleDetails> getEmployees(Date date);
    public List<DailyShiftPOJO> getShifts(Date date);
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId);
    public void addEmpHistory(int employeeId);
    public ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest);
    public ScheduleResponse getScheduleByDateTime(ScheduleRequest request);
    public List<DailyShiftPOJO> getDailyShifts();
    public List<EligibleEmployees> getEligibleEmployees();

}
