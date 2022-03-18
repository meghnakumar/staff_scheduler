package com.scheduler.app.service;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;
import com.scheduler.app.model.repo.DailyShiftRepository;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmployeeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;
    DailyShiftRepository dailyShiftRepository;
    EmployeeHistoryRepository employeeHistoryRepository;

    public List<EmpAvailabilityPOJO> getEmployees(Date date) {
        List<EmpAvailabilityPOJO> empAvailabilityList = empAvailabilityRepository.findByAvailableDate(date);
//        for(EmpAvailabilityPOJO employee: empAvailabilityList) {
//            System.out.println(employee.getEmployee_id());
//        }
        return empAvailabilityList;
    }

    @Override
    public List<DailyShiftPOJO> getShifts(Date date, Time startTime, Time endTime) {
        List<DailyShiftPOJO> dailyShiftList = dailyShiftRepository.findByShiftDateTime(date,startTime,endTime);
        return dailyShiftList;
    }

    @Override
    public List<EmpHistoryPOJO> getEmpHistory(int employeeId) {
        List<EmpHistoryPOJO> empHistoryList = employeeHistoryRepository.findEmpHistoryById(employeeId);
        return empHistoryList;
    }

    @Override
    public void addEmpHistory(int employeeId) {

    }


}
