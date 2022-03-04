package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.StaffAvailabilitiesRequest;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffAvailabilityServiceImpl implements StaffAvailabilityService{

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    EmployeeCredsDTO employeeCredsDTO;
    boolean check = false;

    @Override
    public StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilitiesRequest) {

        StaffAvailabilityResponse staffAvailabilityResponse = new StaffAvailabilityResponse();
        check = verifyStaff(staffAvailabilitiesRequest.get(0).getEmployeeNumber());
        if(check){
            EmpAvailabilityPOJO empAvailabilityPOJO = null;
            for(StaffAvailabilityRequest request: staffAvailabilitiesRequest){
                empAvailabilityPOJO = new EmpAvailabilityPOJO();
                empAvailabilityPOJO.setId(null);
                empAvailabilityPOJO.setEmployeeNumber(request.getEmployeeNumber());
                empAvailabilityPOJO.setEmployee_id(employeeCredsDTO.getId());
                empAvailabilityPOJO.setAvailableDay(request.getAvailableDay());
                empAvailabilityPOJO.setAvailableDate(request.getAvailableDate());
                empAvailabilityPOJO.setStartTime(request.getStartTime());
                empAvailabilityPOJO.setEndTime(request.getEndTime());
                empAvailabilityRepository.saveAndFlush(empAvailabilityPOJO);
            }
            staffAvailabilityResponse.setStatus(REQUEST_STATUS.SUCCESS);
            staffAvailabilityResponse.setEntered(true);
        }
        else{
            staffAvailabilityResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
            staffAvailabilityResponse.setEntered(false);
        }
        return staffAvailabilityResponse;
    }

    @Override
    public boolean verifyStaff(String employeeNumber) {

        employeeCredsDTO = empDetailRepository.getTopByEmployeeNumber(employeeNumber);

        if(null!=employeeCredsDTO && null!=employeeCredsDTO.getEmployeeNumber()){
                return true;
        }
        return false;
    }
}


