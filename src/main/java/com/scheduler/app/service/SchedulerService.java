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
     List<ScheduleDetails> getEmployees(Date date);
     List<DailyShiftPOJO> getShifts(Date date);
     List<EmpHistoryPOJO> getEmpHistory(int employeeId);
     //void addEmpHistory(int employeeId);
     ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest);
     ScheduleResponse getScheduleByDateTime(ScheduleRequest request);
     void algoImplementation();
     List<DailyShiftPOJO> getDailyShifts();
    //public List<EligibleEmployees> getEligibleEmployees();

}
