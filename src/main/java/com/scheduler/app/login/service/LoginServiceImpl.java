package com.scheduler.app.login.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.staff.model.repo.EmpDetailRepository;
import com.scheduler.app.login.model.request.LoginRequest;
import com.scheduler.app.login.model.response.LoginResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Login Service.
 * This service is responsible for the login process. The user enters the credentials in the UI and those credentials are being validated by this service
 * to determine whether the user should be allowed to log into the application. This service sends in its response the type of user who's trying to log in
 * and based on that response, the UI renders specific pages for the user to navigate.
 */
@Service
public class LoginServiceImpl implements LoginService{

    //Numeric values representing types of roles in the databases.
    private static final int ADMIN = 0;
    private static final int SUPERVISOR = 1;
    private static final int STAFF = 2;
    private static final int INTERN = 3 ;

    /**
     * Auto-wired Component : JPA Repo to the Employee Details table.
     */
    @Autowired
    EmpDetailRepository empDetailRepository;



    /**
     * Input credentials method consumes the credentials given by the user on the UI, and
     * validates if the user credentials for the login are valid or not. If the credentials match, the used is allowed to login to the application.
     * Otherwise, the method responds with a BAD_REQUEST status which renders a failed login modal in UI.
     *
     * @param loginRequest the login request type object with the id & password.
     * @return the login response type object.
     */
    @Override
    public LoginResponse inputCredentials(LoginRequest loginRequest) {

        //Check if the input credentials are not null or empty
        if(Strings.isNotEmpty(loginRequest.getUserID()) && Strings.isNotEmpty(loginRequest.getPassword())){

            //Search for the euser in the DB Table.
            EmpDetailPOJO empDetailPOJO = empDetailRepository.getTopByEmployeeNumber(loginRequest.getUserID());
            EmployeeCredsDTO employeeCredsDTO = null;

            //If the user exists in the system - create a new DTO object to store the values.
            if(empDetailPOJO != null) {
                employeeCredsDTO = new EmployeeCredsDTO(empDetailPOJO.getId(), empDetailPOJO.getEmployeeNumber(),
                        empDetailPOJO.getEmailId(), empDetailPOJO.getLoginPassword(), empDetailPOJO.getRoleId(), empDetailPOJO.getDepartmentId());
            }

            //Validate the credentials and Map user type.
            return validateCredentials(loginRequest, empDetailPOJO, employeeCredsDTO);

        } else {

            //Respond with INVALID REQUEST because login id or password were null or empty.
             return  new LoginResponse(REQUEST_STATUS.BAD_REQUEST, false, USER_TYPE.INVALID, -1, loginRequest.getUserID(), "NOT FOUND");
        }
    }


    /**
     * Validates the given credentials and maps the type of user for the user who's trying to log in.
     * Sends USER_TYPE as INVALID if the user is not found in the system.
     *
     * @param loginRequest     the login request type object
     * @param empDetailPOJO    the emp detail pojo
     * @param employeeCredsDTO the employee creds dto
     * @return the login response type object
     */
    @Override
    public LoginResponse validateCredentials(LoginRequest loginRequest, EmpDetailPOJO empDetailPOJO, EmployeeCredsDTO employeeCredsDTO) {

        LoginResponse loginResponse = new LoginResponse();

        //Flag for checking validity of Credentials
        boolean isValidPassword = checkPasswordMatch(employeeCredsDTO, loginRequest);

        if(isValidPassword && empDetailPOJO != null) {

            //Switch user type based on the roleID
            switch (employeeCredsDTO.getRoleId()){


                case ADMIN :    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                            loginResponse.setValid(true);
                            loginResponse.setUserType(USER_TYPE.ADMIN);
                            break;

                case SUPERVISOR :    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                            loginResponse.setValid(true);
                            loginResponse.setUserType(USER_TYPE.SUPERVISOR);
                            break;

                case STAFF :    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                            loginResponse.setValid(true);
                            loginResponse.setUserType(USER_TYPE.STAFF);
                            break;

                case INTERN:    loginResponse.setStatus(REQUEST_STATUS.SUCCESS);
                            loginResponse.setValid(true);
                            loginResponse.setUserType(USER_TYPE.INTERN);
                            break;

                // Respond with Invalid Request.
                default :   loginResponse.setStatus(REQUEST_STATUS.INVALID_REQUEST);
                            loginResponse.setValid(false);
                            loginResponse.setUserType(USER_TYPE.INVALID);
            }

            loginResponse.setId(employeeCredsDTO.getId());
            loginResponse.setEmployeeNumber(employeeCredsDTO.getEmployeeNumber());
            loginResponse.setDepartmentId(employeeCredsDTO.getDepartmentId());

            return loginResponse;

        } else if (empDetailPOJO != null && employeeCredsDTO.getEmployeeNumber().equals(loginRequest.getUserID())){

            //Incorrect Password response.
            return new LoginResponse(REQUEST_STATUS.INCORRECT_PASSWORD, false, USER_TYPE.INVALID, -1, loginRequest.getUserID(), "NOT FOUND");

        } else {

            //Error response.
            return new LoginResponse(REQUEST_STATUS.ERROR, false, USER_TYPE.INVALID, -1, loginRequest.getUserID(), "NOT FOUND");
        }
    }

    //Checks for the password matching between the entered password and the password obtained from the DB for the login ID
    //Returns whether the password matched or not.
    private Boolean checkPasswordMatch(EmployeeCredsDTO employeeCredsDTO, LoginRequest loginRequest) {

        if(employeeCredsDTO != null && loginRequest != null){

            return employeeCredsDTO.getLoginPassword().equals(loginRequest.getPassword());
        }

        return false;
    }
}
