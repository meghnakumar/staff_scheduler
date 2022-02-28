package com.scheduler.app.service;

import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreationServiceImpl implements EmployeeCreationService{

    @Override
    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest) {
        return null;
    }

}
