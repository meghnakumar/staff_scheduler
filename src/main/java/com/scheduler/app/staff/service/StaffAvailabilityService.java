package com.scheduler.app.staff.service;

import com.scheduler.app.staff.model.request.EmployeeAvailabilityExistsRequest;
import com.scheduler.app.staff.model.response.EmployeeAvailabilityExistsResponse;
import com.scheduler.app.staff.model.request.StaffAvailabilityRequest;
import com.scheduler.app.staff.model.response.EmployeeDetailsResponse;
import com.scheduler.app.staff.model.response.StaffAvailabilityResponse;

import java.util.List;

public interface StaffAvailabilityService {

    StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilitiesRequest);

    boolean verifyStaff(String employeeNumber);

    EmployeeDetailsResponse fetchEmployeeInfo(String employeeNumber);

    EmployeeAvailabilityExistsResponse checkEmployeeAvailability(EmployeeAvailabilityExistsRequest request);

}
