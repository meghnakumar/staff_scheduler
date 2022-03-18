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
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    public String department = "Dept01";
    public int roleId = 2;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    DailyShiftRepository dailyShiftRepository;

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    public List<EmpAvailabilityPOJO> getEmployees(Date date) {
//        List<EmpAvailabilityPOJO> empAvailabilityList = empAvailabilityRepository.findByAvailableDate(date);
        List<EmpAvailabilityPOJO> availableEmployees =empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(date, department, roleId);
        for(EmpAvailabilityPOJO employee: availableEmployees) {
            System.out.println(employee.getEmployeeId() +" " +  employee.getStartTime() + " " + employee.getEndTime());
        }
        return availableEmployees;
    }

    @Override
    public List<DailyShiftPOJO> getShifts(Date date) {
        List<DailyShiftPOJO> dailyShiftList = dailyShiftRepository.findByShiftDate(date);
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
