package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreationServiceImpl implements EmployeeCreationService{

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Override
    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest) {

        EmployeeCreationResponse employeeCreationResponse = new EmployeeCreationResponse(REQUEST_STATUS.FAILED,false);
        EmpDetailPOJO newEmployee = new EmpDetailPOJO();

        if(employeeCreationRequest != null && Strings.isNotBlank(employeeCreationRequest.getEmployeeNumber())){

            EmployeeCredsDTO emp = empDetailRepository.getDistinctFirstByEmployeeNumber(employeeCreationRequest.getEmployeeNumber());
            if(emp != null && Strings.isNotBlank(emp.getEmployeeNumber())){

                newEmployee.setId(null);
                newEmployee.setEmployeeNumber(employeeCreationRequest.getEmployeeNumber());
                newEmployee.setEmailId(employeeCreationRequest.getEmailId());
                newEmployee.setDateOfJoining(employeeCreationRequest.getDateOfJoining());
                newEmployee.setDepartmentId(employeeCreationRequest.getDepartmentId());
                newEmployee.setFirstName(employeeCreationRequest.getFirstName());
                //.
                //.
                //.
                //.

            }

        }
        return employeeCreationResponse;

    }

}
