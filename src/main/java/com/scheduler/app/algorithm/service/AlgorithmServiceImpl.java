package com.scheduler.app.algorithm.service;

import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.algorithm.model.repo.ScheduleOutputRespository;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {


    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    ScheduleOutputRespository scheduleOutputRespository;

    @Transactional
    public void truncateScheduleOutput() {
        scheduleOutputRespository.truncateTable();
    }

    public void updateEmployeeHistory(Integer hours, Integer employeeId) {
        EmpHistoryPOJO empHistoryPOJO = employeeHistoryRepository.findDistinctTopByEmployeeId(employeeId);
        if (empHistoryPOJO != null) {
            empHistoryPOJO.setTotalHoursWeekly(hours);
            employeeHistoryRepository.saveAndFlush(empHistoryPOJO);
        }
    }

}
