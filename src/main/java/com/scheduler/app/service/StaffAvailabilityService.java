package com.scheduler.app.service;

import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.StaffAvailabilityResponse;

import java.util.List;

public interface StaffAvailabilityService {

    StaffAvailabilityResponse inputStaffAvailability(List<StaffAvailabilityRequest> staffAvailabilitiesRequest);

    boolean verifyStaff(String employeeNumber);

}
