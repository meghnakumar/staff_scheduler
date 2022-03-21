package com.scheduler.app.service;

import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;

public interface EmployeeCreationService {

    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest);

}
