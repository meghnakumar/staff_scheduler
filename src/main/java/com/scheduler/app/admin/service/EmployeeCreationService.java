package com.scheduler.app.admin.service;

import com.scheduler.app.admin.model.request.EmployeeCreationRequest;
import com.scheduler.app.admin.model.response.EmployeeCreationResponse;

/**
 * The interface for Employee Creation Service.
 */
public interface EmployeeCreationService {

    /**
     * Create new Employee, and return the status of creation via the EmployeeCreationResponse object.
     *
     * @param employeeCreationRequest the employee creation request, with fields for creating the new employee.
     * @return the employee creation response type, with status.
     */
    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest);

}
