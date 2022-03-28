package com.scheduler.app.service;

import com.scheduler.app.model.entity.*;
import com.scheduler.app.model.request.ScheduleOutputRequest;
import com.scheduler.app.model.request.ShiftDetailsRequest;
import com.scheduler.app.model.response.ScheduleOutputResponse;
import com.scheduler.app.model.response.ShiftDetailsResponse;

import java.sql.Date;
import java.util.List;

public interface SchedulerService {
     List<ScheduleDetails> getEmployees(Date date);
     List<DailyShiftPOJO> getShifts(Date date);
     List<EmpHistoryPOJO> getEmpHistory(int employeeId);
     ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest);
     ScheduleOutputResponse getScheduleByDateTimeDepartment(ScheduleOutputRequest request);
     List<DailyShiftPOJO> getDailyShifts();
     void algoImplementation();
}
