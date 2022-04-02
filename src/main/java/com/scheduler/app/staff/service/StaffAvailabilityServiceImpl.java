package com.scheduler.app.staff.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.admin.model.dto.EmployeeDetailDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJO;
import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJOId;
import com.scheduler.app.staff.model.repo.EmpAvailabilityRepository;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.algorithm.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.staff.model.request.EmployeeAvailabilityExistsRequest;
import com.scheduler.app.staff.model.response.EmployeeAvailabilityExistsResponse;
import com.scheduler.app.staff.model.request.StaffAvailabilityRequest;
import com.scheduler.app.staff.model.response.EmployeeDetailsResponse;
import com.scheduler.app.staff.model.response.StaffAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffAvailabilityServiceImpl implements StaffAvailabilityService {

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    EmployeeCredsDTO employeeCredsDTO;

    EmpDetailPOJO empDetailPOJO;

    boolean check = false;

    @Override
    public StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilitiesRequest) {

        StaffAvailabilityResponse staffAvailabilityResponse = new StaffAvailabilityResponse();
        check = verifyStaff(staffAvailabilitiesRequest.get(0).getEmployeeNumber());
        if (check) {
            EmployeeAvailabilityPOJO employeeAvailabilityPOJO = null;
            EmployeeAvailabilityPOJOId employeeAvailabilityPOJOId = null;
            EmpHistoryPOJO empHistoryPOJO = null;
            for (StaffAvailabilityRequest request : staffAvailabilitiesRequest) {
                Date availableDate = Date.valueOf(request.getAvailableDate());
                employeeAvailabilityPOJOId = new EmployeeAvailabilityPOJOId(request.getEmployeeNumber(), availableDate);
                boolean exists = checkIfAvailabilityAlreadyGiven(employeeAvailabilityPOJOId);
                if (exists) {
                    employeeAvailabilityPOJO = empAvailabilityRepository.getById(employeeAvailabilityPOJOId);
                    employeeAvailabilityPOJO.setStartTime(getTime(request.getStartTime()));
                    employeeAvailabilityPOJO.setEndTime(getTime(request.getEndTime()));
                } else {
                    employeeAvailabilityPOJO = new EmployeeAvailabilityPOJO();
                    employeeAvailabilityPOJO.setId(employeeAvailabilityPOJOId);
                    employeeAvailabilityPOJO.setDepartmentId(empDetailPOJO.getDepartmentId());
                    employeeAvailabilityPOJO.setRoleId(empDetailPOJO.getRoleId());
                    employeeAvailabilityPOJO.setShiftDay(request.getAvailableDay());
                    employeeAvailabilityPOJO.setStartTime(getTime(request.getStartTime()));
                    employeeAvailabilityPOJO.setEndTime(getTime(request.getEndTime()));
                }
                empHistoryPOJO = new EmpHistoryPOJO();
                empHistoryPOJO.setEmployeeId(empDetailPOJO.getId());
                empHistoryPOJO.setTotalHoursWeekly(0);
                empAvailabilityRepository.saveAndFlush(employeeAvailabilityPOJO);
                employeeHistoryRepository.saveAndFlush(empHistoryPOJO);

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
        return LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
    }

    public boolean checkIfAvailabilityAlreadyGiven(EmployeeAvailabilityPOJOId id) {
        Optional<EmployeeAvailabilityPOJO> result = empAvailabilityRepository.findById(id);
        return result.isPresent();
    }


    public EmployeeAvailabilityExistsResponse checkEmployeeAvailability(EmployeeAvailabilityExistsRequest request) {
        EmployeeAvailabilityExistsResponse employeeAvailabilityExistsResponse = new EmployeeAvailabilityExistsResponse();
        String employeeNumber = request.getEmployeeNumber();
        EmployeeAvailabilityPOJOId employeeAvailabilityPOJOId = null;
        List<String> dates = request.getDates();
        ArrayList<Date> convertedDates = new ArrayList<>();
        ArrayList<String> alreadyExisitngDates = new ArrayList<>();
        boolean modified = false;
        for (String date : dates) {
            convertedDates.add(Date.valueOf(date));
        }
        for(Date date: convertedDates){
            employeeAvailabilityPOJOId = new EmployeeAvailabilityPOJOId(employeeNumber,date);
            if(empAvailabilityRepository.existsById(employeeAvailabilityPOJOId)){
                alreadyExisitngDates.add(date.toLocalDate().toString());
                modified = true;
            }
        }
        employeeAvailabilityExistsResponse.setModified(modified);
        employeeAvailabilityExistsResponse.setStatus(REQUEST_STATUS.SUCCESS);
        employeeAvailabilityExistsResponse.setDates(alreadyExisitngDates);
        return employeeAvailabilityExistsResponse;
    }
}


