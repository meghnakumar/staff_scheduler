package com.scheduler.app.login.service;

import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.login.model.request.LoginRequest;
import com.scheduler.app.login.model.response.LoginResponse;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;

/**
 * The interface for Login service.
 */
public interface LoginService {

     /**
      * Input credentials method consumes the credentials given by the user on the UI, and
      * validates if the user credentials for the login are valid or not. If the credentials match, the used is allowed to login to the application.
      * Otherwise, the method responds with a BAD_REQUEST status which renders a failed login modal in UI.
      *
      * @param loginRequest the login request type object with the id & password.
      * @return the login response type object.
      */
     LoginResponse inputCredentials(LoginRequest loginRequest);


     /**
      * Validates the given credentials and maps the type of user for the user who's trying to log in.
      * Sends USER_TYPE as INVALID if the user is not found in the system.
      *
      * @param loginRequest     the login request type object
      * @param empDetailPOJO    the emp detail pojo
      * @param employeeCredsDTO the employee creds dto
      * @return the login response type object
      */
     LoginResponse validateCredentials(LoginRequest loginRequest, EmpDetailPOJO empDetailPOJO, EmployeeCredsDTO employeeCredsDTO);
}
