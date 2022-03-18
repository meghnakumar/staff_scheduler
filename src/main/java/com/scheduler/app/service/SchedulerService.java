package com.scheduler.app.service;

import com.scheduler.app.model.entity.BookDTO;
import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface SchedulerService {
    public List<EmpAvailabilityPOJO> getEmployees(Date date);
    public List<DailyShiftPOJO> getShifts(Date date, Time startTime, Time endTime);
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId);
    public void addEmpHistory(int employeeId);

}
