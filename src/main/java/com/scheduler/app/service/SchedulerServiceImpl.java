package com.scheduler.app.service;

import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    public List<EmpAvailabilityPOJO> getEmployees(Date date) {
        List<EmpAvailabilityPOJO> empAvailabilityList = empAvailabilityRepository.findByAvailableDate(date);
//        for(EmpAvailabilityPOJO employee: empAvailabilityList) {
//            System.out.println(employee.getEmployee_id());
//        }
        return empAvailabilityList;
    }
}
