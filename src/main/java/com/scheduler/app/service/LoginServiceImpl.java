package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.model.dto.EmployeeCredsDTO;
import com.scheduler.app.model.repo.EmpDetailRepository;
import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    EmpDetailRepository empDetailRepository;

    public LoginResponse inputCredentials(LoginRequest loginRequest) {

        LoginResponse loginResponse = new LoginResponse();
        if(Strings.isNotEmpty(loginRequest.getUserID()) && Strings.isNotEmpty(loginRequest.getPassword())){

            Integer empID = Integer.parseInt(loginRequest.getUserID());
            EmployeeCredsDTO employeeCredsDTO = empDetailRepository.getLoginCredsById(empID);

            //Flag for checking validity of Credentials
            boolean isValidPassword = checkPasswordMatch(employeeCredsDTO, loginRequest);
            if(isValidPassword && employeeCredsDTO != null) {

                switch (employeeCredsDTO.getJobType()){


                    case 1 :    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                                loginResponse.setValid(true);
                                loginResponse.setUserType(USER_TYPE.ADMIN);
                                break;

                    case 2 :    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                                loginResponse.setValid(true);
                                loginResponse.setUserType(USER_TYPE.SUPERVISOR);
                                break;

                    case 3 :    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                                loginResponse.setValid(true);
                                loginResponse.setUserType(USER_TYPE.STAFF);
                                break;

                    default :   loginResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
                                loginResponse.setValid(false);
                                loginResponse.setUserType(USER_TYPE.INVALID);
                }

            } else if (employeeCredsDTO != null && employeeCredsDTO.getId().equals(empID)){

                loginResponse.setStatus(REQUEST_STATUS.INCORRECT_PASSWORD);
                loginResponse.setValid(false);
                loginResponse.setUserType(USER_TYPE.INVALID);

            } else {

                loginResponse.setStatus(REQUEST_STATUS.ERROR);
                loginResponse.setValid(false);
                loginResponse.setUserType(USER_TYPE.INVALID);

            }

            return loginResponse;

        } else {

            loginResponse.setStatus(REQUEST_STATUS.BAD_REQUEST);
            loginResponse.setValid(false);
            loginResponse.setUserType(USER_TYPE.INVALID);
        }

        return loginResponse;
    }

    private Boolean checkPasswordMatch(EmployeeCredsDTO employeeCredsDTO, LoginRequest loginRequest) {

        if(employeeCredsDTO != null && loginRequest != null){

            return employeeCredsDTO.getLoginPassword().equals(loginRequest.getPassword());
        }

        return false;
    }
}
