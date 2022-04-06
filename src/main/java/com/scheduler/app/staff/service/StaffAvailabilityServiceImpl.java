package com.scheduler.app.staff.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.staff.model.dto.EmployeeDetailDTO;
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
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Staff Availability Service.
 * This service is responsible for handling the processing of the availability for a staff member in the system.
 * This service inputs and fetches information from the employee availability tables in the database.
 */
@Service
public class StaffAvailabilityServiceImpl implements StaffAvailabilityService {

    /**
     * Auto-wired Component : JPA Repo to the 'empdetails' table.
     */
    @Autowired
    EmpDetailRepository empDetailRepository;

    /**
     * Auto-wired Component : JPA Repo to the 'empdetails' table.
     */
    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    /**
     * Auto-wired Component : JPA Repo to the 'emphistory' table.
     */
    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    /**
     * The Emp detail pojo.
     */
    EmpDetailPOJO empDetailPOJO;

    /**
     * The check to see if the employee exists or not. Defaults to false.
     */
    boolean checkIfExists = false;

    /**
     * Inputs the availability of the staff member as they provide it for the coming week in the Front-end.
     * This availability data is later utilised by the Algorithm for generating the schedule.
     *
     * @param staffAvailabilityRequest the staff availabilities request type object.
     * @return the staff availability response type object.
     */
    @Override
    public StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilityRequest) {

        //Check if the Staff member exists in the DB
        checkIfExists = verifyStaff(staffAvailabilityRequest.get(0).getEmployeeNumber());
        if (checkIfExists) {

            EmployeeAvailabilityPOJO employeeAvailabilityPOJO = null;
            EmployeeAvailabilityPOJOId employeeAvailabilityPOJOId = null;
            EmpHistoryPOJO empHistoryPOJO = null;
            for (StaffAvailabilityRequest request : staffAvailabilityRequest) {
                Date availableDate = Date.valueOf(request.getAvailableDate());
                employeeAvailabilityPOJOId = new EmployeeAvailabilityPOJOId(request.getEmployeeNumber(), availableDate);
                boolean exists = checkIfAvailabilityAlreadyGiven(employeeAvailabilityPOJOId);
                if (exists) {
                    employeeAvailabilityPOJO = empAvailabilityRepository.getById(employeeAvailabilityPOJOId);
                    employeeAvailabilityPOJO.setStartTime(Time.valueOf(request.getStartTime()));
                    employeeAvailabilityPOJO.setEndTime(Time.valueOf(request.getEndTime()));
                } else {
                    employeeAvailabilityPOJO = new EmployeeAvailabilityPOJO();
                    employeeAvailabilityPOJO.setId(employeeAvailabilityPOJOId);
                    employeeAvailabilityPOJO.setDepartmentId(empDetailPOJO.getDepartmentId());
                    employeeAvailabilityPOJO.setRoleId(empDetailPOJO.getRoleId());
                    employeeAvailabilityPOJO.setShiftDay(request.getAvailableDay());
                    employeeAvailabilityPOJO.setStartTime(Time.valueOf(request.getStartTime()));
                    employeeAvailabilityPOJO.setEndTime(Time.valueOf(request.getEndTime()));
                    employeeAvailabilityPOJO.setEmployeeId(empDetailPOJO.getId());
                }
                empHistoryPOJO = new EmpHistoryPOJO();
                empHistoryPOJO.setEmployeeId(empDetailPOJO.getId());
                boolean ifExist = employeeHistoryRepository.existsByEmployeeId(empDetailPOJO.getId());
                if (!ifExist){
                    empHistoryPOJO.setTotalHoursWeekly(0);
                }
                empAvailabilityRepository.saveAndFlush(employeeAvailabilityPOJO);
                employeeHistoryRepository.saveAndFlush(empHistoryPOJO);

            }

            return new StaffAvailabilityResponse(REQUEST_STATUS.SUCCESS, true);

        } else {

            return new StaffAvailabilityResponse(REQUEST_STATUS.INVALID_REQUEST, false);
        }
    }


    /**
     * Verify if the staff member with the given employee number exists or not.
     *
     * @param employeeNumber the employee number
     * @return whether the employee exists in the DB or not.
     */
    @Override
    public boolean verifyStaff(String employeeNumber) {

        empDetailPOJO = empDetailRepository.getTopByEmployeeNumber(employeeNumber);

        return null != empDetailPOJO && null != empDetailPOJO.getEmployeeNumber();
    }


    /**
     * Fetches the information of the staff member for the given employee number.
     * The information is the basic information associated with that employee in the system
     * which will be displayed on the staff homepage.
     *
     * @param employeeNumber the employee number
     * @return the employee details response type object.
     */
    @Override
    public EmployeeDetailsResponse fetchEmployeeInfo(String employeeNumber) {

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

            return new EmployeeDetailsResponse(REQUEST_STATUS.SUCCESS, employeeDetailDTO);

        } else {

            return new EmployeeDetailsResponse(REQUEST_STATUS.INVALID_REQUEST, new EmployeeDetailDTO());
        }

    }

    /**
     * Gets LocalTime object from the time values given as a string.
     *
     * @param time the time in string format.
     * @return the LocalTime object.
     */
    private LocalTime getTime(String time) {
        String[] splitTime = time.split(":");
        return LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
    }

    /**
     * Check if availability already given.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean checkIfAvailabilityAlreadyGiven(EmployeeAvailabilityPOJOId id) {
        Optional<EmployeeAvailabilityPOJO> result = empAvailabilityRepository.findById(id);
        return result.isPresent();
    }


    /**
     * Checks if the employee availability already exists or not.
     * If it does, returns the object that contains the information about the availability.
     *
     * @param request the employee availability exists request type object.
     * @return the employee availability exists response type object.
     */
    @Override
    public EmployeeAvailabilityExistsResponse checkEmployeeAvailability(EmployeeAvailabilityExistsRequest request) {

        EmployeeAvailabilityExistsResponse employeeAvaExiResponse = new EmployeeAvailabilityExistsResponse();
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
        employeeAvaExiResponse.setModified(modified);
        employeeAvaExiResponse.setStatus(REQUEST_STATUS.SUCCESS);
        employeeAvaExiResponse.setDates(alreadyExisitngDates);
        return employeeAvaExiResponse;
    }
}


