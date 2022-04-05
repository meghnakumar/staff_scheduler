package com.scheduler.app.staff.service;

import com.scheduler.app.staff.model.request.EmployeeAvailabilityExistsRequest;
import com.scheduler.app.staff.model.response.EmployeeAvailabilityExistsResponse;
import com.scheduler.app.staff.model.request.StaffAvailabilityRequest;
import com.scheduler.app.staff.model.response.EmployeeDetailsResponse;
import com.scheduler.app.staff.model.response.StaffAvailabilityResponse;

import java.util.List;

/**
 * The interface for Staff Availability Service.
 */
public interface StaffAvailabilityService {

    /**
     * Inputs the availability of the staff member as they provide it for the coming week in the Front-end.
     * This availability data is later utilised by the Algorithm for generating the schedule.
     *
     * @param staffAvailabilitiesRequest the staff availabilities request type object.
     * @return the staff availability response type object.
     */
    StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilitiesRequest);

    /**
     * Verify if the staff member with the given employee number exists or not.
     *
     * @param employeeNumber the employee number
     * @return whether the employee exists in the DB or not.
     */
    boolean verifyStaff(String employeeNumber);

    /**
     * Fetches the information of the staff member for the given employee number.
     * The information is the basic information associated with that employee in the system
     * which will be displayed on the staff homepage.
     *
     * @param employeeNumber the employee number
     * @return the employee details response type object.
     */
    EmployeeDetailsResponse fetchEmployeeInfo(String employeeNumber);

    /**
     * Checks if the employee availability already exists or not.
     * If it does, returns the object that contains the information about the availability.
     *
     * @param request the employee availability exists request type object.
     * @return the employee availability exists response type object.
     */
    EmployeeAvailabilityExistsResponse checkEmployeeAvailability(EmployeeAvailabilityExistsRequest request);

}
