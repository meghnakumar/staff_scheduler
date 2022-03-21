package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.ScheduleCompositeId;
import com.scheduler.app.model.entity.SchedulePOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.ScheduleRepository;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public List<EmpAvailabilityPOJO> getEmployees(Date date) {
        List<EmpAvailabilityPOJO> empAvailabilityList = empAvailabilityRepository.findByAvailableDate(date);
//        for(EmpAvailabilityPOJO employee: empAvailabilityList) {
//            System.out.println(employee.getEmployee_id());
//        }
        return empAvailabilityList;
    }

    @Override
    public ScheduleResponse getScheduleByDateTime(ScheduleRequest scheduleRequest) {

        if(scheduleRequest.getShiftDate() == null && scheduleRequest.getShiftTime() == null){
            return new ScheduleResponse(REQUEST_STATUS.INVALID_REQUEST, false, Collections.emptyMap());

        } else {

            ScheduleCompositeId compositeId = new ScheduleCompositeId(scheduleRequest.getShiftDate(), scheduleRequest.getShiftTime());
            Map<String, SchedulePOJO> schedule = new HashMap<>();
            Optional<SchedulePOJO> scheduleOutput = scheduleRepository.findById(compositeId);

            if (scheduleOutput.isPresent()) {

                schedule.put(scheduleOutput.get().getDepartment().getId(), scheduleOutput.get());
                return new ScheduleResponse(REQUEST_STATUS.SUCCESS, true, schedule);
            } else {

                return new ScheduleResponse(REQUEST_STATUS.SUCCESS, false,  Collections.emptyMap());
            }

        }
    }
}
