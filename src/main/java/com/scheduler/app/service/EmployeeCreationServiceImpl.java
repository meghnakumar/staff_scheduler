package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.entity.EmpDetailPOJO;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;
import com.scheduler.app.util.MailService;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeCreationServiceImpl implements EmployeeCreationService{

    @Autowired
    EmpDetailRepository empDetailRepository;

    @Autowired
    MailService mailService;

    @Override
    public EmployeeCreationResponse createNewEmployee(EmployeeCreationRequest employeeCreationRequest) {

        EmployeeCreationResponse employeeCreationResponse = new EmployeeCreationResponse(REQUEST_STATUS.FAILED,false);
        EmpDetailPOJO newEmployee = new EmpDetailPOJO();

        if(employeeCreationRequest != null && Strings.isNotBlank(employeeCreationRequest.getEmployeeNumber())){

            EmployeeCredsDTO emp = empDetailRepository.getDistinctFirstByEmployeeNumber(employeeCreationRequest.getEmployeeNumber());
            if(emp == null && Strings.isNotBlank(employeeCreationRequest.getEmployeeNumber())){


                String generatedPassword = generateCommonTextPassword();
                System.out.println(generatedPassword);
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
                newEmployee.setLoginPassword(generatedPassword);
                EmpDetailPOJO employee = empDetailRepository.saveAndFlush(newEmployee);
                if(employee != null){
                    employeeCreationResponse.setStatus(REQUEST_STATUS.SUCCESS);
                    employeeCreationResponse.setCreated(true);
                    // to initiate mail to share credentials to newly created employee
                    mailService.sendMail(employee.getEmailId(),"Staff Scheduler Team | New Employee Added", "Your account has been created in Staff Scheduler. Your user ID is your Email ID.\\nYour employee ID is "+employee.getEmployeeNumber()+ ".\\nYour password is -- "+ employee.getLoginPassword()+" --.");
                    return employeeCreationResponse;
                }
            } else if (emp != null && emp.getEmployeeNumber().equals(employeeCreationRequest.getEmployeeNumber())){

                employeeCreationResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
                employeeCreationResponse.setCreated(false);

                }
            }

        return employeeCreationResponse;

    }


    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 64).build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57).build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomAlphabet(int length, boolean flag) {
        RandomStringGenerator pwdGenerator = null;
        if (flag) {
//			pwdGenerator = new RandomStringGenerator.Builder().withinRange(97, 122).build();
            pwdGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        } else {
            pwdGenerator = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();
        }
        return pwdGenerator.generate(length);
    }

    public String generateRandomCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
        return pwdGenerator.generate(length);
    }

    public String generateCommonTextPassword() {
//    	org.apache.commons.lang3.RandomStringUtils
//    	RandomPasswordGenerator passGen = new RandomPasswordGenerator();
//        String password = passGen.generateCommonTextPassword();

        String pwString = generateRandomSpecialCharacters(2).concat(generateRandomNumbers(2))
                .concat(generateRandomAlphabet(2, true))
                .concat(generateRandomAlphabet(2, false))
                .concat(generateRandomCharacters(2));
        List<Character> pwChars = pwString.chars()
                .mapToObj(data -> (char) data)
                .collect(Collectors.toList());
        Collections.shuffle(pwChars);
        return pwChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

}
