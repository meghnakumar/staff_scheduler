package com.scheduler.app.service;

import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;

public interface EmployeeCreationService {

    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest);

}
