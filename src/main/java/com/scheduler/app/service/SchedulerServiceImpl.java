package com.scheduler.app.service;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
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

    public List<EmpAvailabilityPOJO> getEmployees(Date date) {
//        List<EmpAvailabilityPOJO> empAvailabilityList = empAvailabilityRepository.findByAvailableDate(date);
        List<EmpAvailabilityPOJO> availableEmployees =empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(date, department, roleId);
        for(EmpAvailabilityPOJO employee: availableEmployees) {
            System.out.println(employee.getEmployeeId() +" " +  employee.getStartTime() + " " + employee.getEndTime());
        }
        return availableEmployees;
    }
}
