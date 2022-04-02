package com.scheduler.app.supervisor.service;

import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import com.scheduler.app.algorithm.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;

import java.sql.Date;
import java.util.List;

public interface SchedulerService {
//     List<ScheduleDetails> getEmployees(Date date);
     List<DailyShiftPOJO> getShifts(Date date);
     List<EmpHistoryPOJO> getEmpHistory(int employeeId);
     ShiftDetailsResponse saveShiftDetails(ShiftDetailsRequest shiftDetailsRequest);
     ScheduleOutputResponse getScheduleByDateTimeDepartment(ScheduleOutputRequest request);
     List<DailyShiftPOJO> getDailyShifts();
     void algoImplementation();
}
