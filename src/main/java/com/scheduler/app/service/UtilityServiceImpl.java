package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.DepartmentPOJO;
import com.scheduler.app.model.entity.HolidayPOJO;
import com.scheduler.app.model.repo.DepartmentRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.repo.HolidayRepository;
import com.scheduler.app.model.response.InfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UtilityServiceImpl implements  UtilityService{

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    HolidayRepository holidayRepository;

    @Override
    public InfoResponse getStatistics(Boolean onload) {

        if(Boolean.TRUE.equals(onload)){

            Long numEmployees = empDetailRepository.count();
            Long numDepartments = departmentRepository.count();
            List<DepartmentPOJO> deptDetails = departmentRepository.findAll();
            LocalDate date = LocalDate.now();
            Date dateSQL = Date.valueOf(date);
            Date dateEnd = Date.valueOf(date.plusDays(7));
            List<HolidayPOJO> upcomingHolidays = holidayRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(dateSQL, dateEnd);

            Map<String, String> deptInfo = new HashMap<>();
            for (DepartmentPOJO dept: deptDetails) {
                deptInfo.put(dept.getId(), dept.getDepartmentName());
            }

            Map<String, String> holidayInfo = new HashMap<>();
            for (HolidayPOJO holiday: upcomingHolidays) {
                holidayInfo.put(holiday.getStartDate().toString(),holiday.getHolidayTitle());
            }

            return new InfoResponse(numEmployees, numDepartments, deptInfo, holidayInfo, REQUEST_STATUS.SUCCESS);
        } else {

            return new InfoResponse(0l,0l, Collections.emptyMap(), Collections.emptyMap(),REQUEST_STATUS.BAD_REQUEST);
        }
    }
}
