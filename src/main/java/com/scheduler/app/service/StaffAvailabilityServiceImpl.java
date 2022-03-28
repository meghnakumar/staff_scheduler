package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.dto.EmployeeDetailDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import com.scheduler.app.model.entity.EmployeeAvailabilityPOJO;
import com.scheduler.app.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.EmployeeDetailsResponse;
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
            EmployeeAvailabilityPOJO employeeAvailabilityPOJO = null;
            for (StaffAvailabilityRequest request : staffAvailabilitiesRequest) {
                Date availableDate = Date.valueOf(request.getAvailableDate());
                boolean exists = checkIfAvailabilityAlreadyGiven(empDetailPOJO.getId(), availableDate);
                employeeAvailabilityPOJO = new EmployeeAvailabilityPOJO();
                employeeAvailabilityPOJO.setId(null);
                employeeAvailabilityPOJO.setEmployeeId(empDetailPOJO.getId());
                employeeAvailabilityPOJO.setDepartmentId(empDetailPOJO.getDepartmentId());
                employeeAvailabilityPOJO.setRoleId(empDetailPOJO.getRoleId());
                employeeAvailabilityPOJO.setShiftDate(availableDate);
                employeeAvailabilityPOJO.setShiftDay(request.getAvailableDay());
                employeeAvailabilityPOJO.setStartTime(getTime(request.getStartTime()));
                employeeAvailabilityPOJO.setEndTime(getTime(request.getEndTime()));
                empAvailabilityRepository.saveAndFlush(employeeAvailabilityPOJO);
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

        if (null != empDetailPOJO && null != empDetailPOJO.getEmployeeNumber()) {
            return true;
        }
        return false;
    }

    @Override
    public EmployeeDetailsResponse fetchEmployeeInfo(String employeeNumber) {
        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        empDetailPOJO = empDetailRepository.getTopByEmployeeNumber(employeeNumber);
        if (null != empDetailPOJO) {
            EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
            employeeDetailDTO.setEmployeeNumber(empDetailPOJO.getEmployeeNumber());
            employeeDetailDTO.setDateOfJoining(empDetailPOJO.getDateOfJoining());
            employeeDetailDTO.setEmailId(empDetailPOJO.getEmailId());
            employeeDetailDTO.setFirstName(empDetailPOJO.getFirstName());
            employeeDetailDTO.setJobType(empDetailPOJO.getJobType());
            employeeDetailDTO.setMaxAvailabilityHours(empDetailPOJO.getMaxAvailabilityHours());
            employeeDetailDTO.setDepartmentId(empDetailPOJO.getDepartmentId());
            employeeDetailDTO.setLastName(empDetailPOJO.getLastName());
            employeeDetailDTO.setPhoneNumber(empDetailPOJO.getPhoneNumber());
            employeeDetailDTO.setSinNumber(empDetailPOJO.getSinNumber());
            employeeDetailDTO.setDateOfJoining(empDetailPOJO.getDateOfJoining());
            employeeDetailDTO.setRoleId(empDetailPOJO.getRoleId());
            employeeDetailsResponse.setStatus(REQUEST_STATUS.SUCCESS);
            employeeDetailsResponse.setResponse(employeeDetailDTO);
        } else {
            employeeDetailsResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
        }
        return employeeDetailsResponse;
    }

    private LocalTime getTime(String time) {
        String[] splitTime = time.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
        return localTime;
    }

    public boolean checkIfAvailabilityAlreadyGiven(Integer employeeNumber, Date availableDate) {
        EmployeeAvailabilityPOJO result = empAvailabilityRepository.findEmployeeAvailabilityPOJOByShiftDateAndEmployeeId(availableDate, employeeNumber);
        if (result != null) {
            return result.getEmployeeId() != null;
        }
        return false;
    }
}


