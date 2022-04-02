package com.scheduler.app.admin.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.admin.model.request.EmployeeCreationRequest;
import com.scheduler.app.admin.model.response.EmployeeCreationResponse;
import com.scheduler.app.util.MailService;
import com.scheduler.app.util.RandomTextGeneratorUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Employee Creation Service.
 * This Service helps in the creation & registration of a new Employee in the System.
 * All the details for the new Employee are received form the front-end.
 */
@Service
public class EmployeeCreationServiceImpl implements EmployeeCreationService {

    /**
     * Auto-wired Component : JPA Repo to the Emp Details Table.
     */
    @Autowired
    EmpDetailRepository empDetailRepository;

    /**
     * Auto-wired Component : Utility Service to Send Email sto Newly Created Employees.
     */
    @Autowired
    MailService mailService;

    /**
     * Create new Employee, and return the status of creation via the EmployeeCreationResponse object.
     *
     * @param employeeCreationRequest the employee creation request, with fields for creating the new employee.
     * @return the employee creation response type, with status.
     */
    @Override
    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest) {

        EmployeeCreationResponse employeeCreationResponse = new EmployeeCreationResponse(REQUEST_STATUS.FAILED, false);
        EmpDetailPOJO newEmployee = new EmpDetailPOJO();

        //Check if the Request object is not null & has the mandatory filed Employee Number
        if (employeeCreationRequest != null && Strings.isNotBlank(employeeCreationRequest.getEmployeeNumber())) {

            //Check if an Employee with the same Employee Number already exists in the system.
            EmployeeCredsDTO emp = empDetailRepository.getDistinctFirstByEmployeeNumber(employeeCreationRequest.getEmployeeNumber());
            if (emp == null && Strings.isNotBlank(employeeCreationRequest.getEmployeeNumber())) {

                // To generate a random password for first time login
                //Will be sent to the new Employee via an email.
                RandomTextGeneratorUtil passwordGenerator = new RandomTextGeneratorUtil();
                String generatedPassword = passwordGenerator.generateCommonTextPassword();

                //Create a new Employee with request values.
                newEmployee.setId(null);
                newEmployee.setEmployeeNumber(employeeCreationRequest.getEmployeeNumber());
                newEmployee.setEmailId(employeeCreationRequest.getEmailId());
                newEmployee.setDateOfJoining(employeeCreationRequest.getDateOfJoining());
                newEmployee.setDepartmentId(employeeCreationRequest.getDepartmentId());
                newEmployee.setFirstName(employeeCreationRequest.getFirstName());
                newEmployee.setLastName(employeeCreationRequest.getLastName());
                newEmployee.setPhoneNumber(employeeCreationRequest.getPhoneNumber());
                newEmployee.setJobType(employeeCreationRequest.getJobType());
                newEmployee.setMaxAvailabilityHours(employeeCreationRequest.getMaxAvailabilityHours());
                newEmployee.setRoleId(employeeCreationRequest.getRoleId());
                newEmployee.setSinNumber(employeeCreationRequest.getSinNumber());
                newEmployee.setPhoto(employeeCreationRequest.getPhoto());
                //Set one-time password.
                newEmployee.setLoginPassword(generatedPassword);
                //Save the Employee in the DB.
                EmpDetailPOJO employee = empDetailRepository.saveAndFlush(newEmployee);

                if (employee != null) {
                    //Successful Response.
                    employeeCreationResponse.setStatus(REQUEST_STATUS.SUCCESS);
                    employeeCreationResponse.setCreated(true);
                    // to initiate mail to share credentials to newly created employee
                    String subject = "Account Confirmation | Staff Scheduler Team ";
                    String message = "Your account has been created in Staff Scheduler.<br> Your user ID is your Email ID.<br>Your employee ID is <b>" + employee.getEmployeeNumber() + "</b><br>Your password is <b>" + employee.getLoginPassword() + "</b>.<br>Please keep your credentials safe.";
                    mailService.sendMailToEmployee(employee, subject, message);
                    return employeeCreationResponse;
                }

                //Response in case the Employee with the same number already exists.
            } else if (emp != null && emp.getEmployeeNumber().equals(employeeCreationRequest.getEmployeeNumber())) {

                employeeCreationResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
                employeeCreationResponse.setCreated(false);
            }
        }

        //Return EmployeeCreationResponse
        return employeeCreationResponse;

    }


}
