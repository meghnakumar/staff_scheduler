package com.scheduler.app.service;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;

import java.sql.Date;
import java.util.List;

public interface SchedulerService {
    public List<EmpAvailabilityPOJO> getEmployees(Date date);
}
