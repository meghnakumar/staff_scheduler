package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Service
public class StaffAvailabilityServiceImpl implements StaffAvailabilityService {

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    EmployeeCredsDTO employeeCredsDTO;

    EmpDetailPOJO empDetailPOJO;

    boolean check = false;

    @Override
    public StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilitiesRequest) {

        StaffAvailabilityResponse staffAvailabilityResponse = new StaffAvailabilityResponse();
        check = verifyStaff(staffAvailabilitiesRequest.get(0).getEmployeeNumber());
        if (check) {
            EmpAvailabilityPOJO empAvailabilityPOJO = null;
            for (StaffAvailabilityRequest request : staffAvailabilitiesRequest) {
                empAvailabilityPOJO = new EmpAvailabilityPOJO();
                empAvailabilityPOJO.setId(null);
                empAvailabilityPOJO.setEmployeeNumber(request.getEmployeeNumber());
                empAvailabilityPOJO.setEmployeeId(empDetailPOJO.getId());
                empAvailabilityPOJO.setAvailableDay(request.getAvailableDay());
                empAvailabilityPOJO.setAvailableDate(request.getAvailableDate());
                empAvailabilityPOJO.setStartTime(request.getStartTime());
                empAvailabilityPOJO.setEndTime(request.getEndTime());
                empAvailabilityRepository.saveAndFlush(empAvailabilityPOJO);
            }
            staffAvailabilityResponse.setStatus(REQUEST_STATUS.SUCCESS);
            staffAvailabilityResponse.setEntered(true);
        } else {
            staffAvailabilityResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
            staffAvailabilityResponse.setEntered(false);
        }
        return staffAvailabilityResponse;
    }

    @Override
    public boolean verifyStaff(String employeeNumber) {

        empDetailPOJO = empDetailRepository.getTopByEmployeeNumber(employeeNumber);

        if(null!=empDetailPOJO && null!=empDetailPOJO.getEmployeeNumber()){
            return true;
        }
        return false;
    }

    private LocalTime getTime(String time) {
        String[] splitTime = time.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
        return localTime;
    }

    public boolean checkIfAvailabilityAlreadyGiven(Integer employeeNumber, Date availableDate){
        EmployeeAvailabilityPOJO result = empAvailabilityRepository.findEmployeeAvailabilityPOJOByShiftDateAndEmployeeId(availableDate, employeeNumber);
        if(result!= null){
            return result.getEmployeeId() != null;
        }
        return false;
    }
}


