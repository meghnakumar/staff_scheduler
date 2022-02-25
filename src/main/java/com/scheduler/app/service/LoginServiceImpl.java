package com.scheduler.app.service;

import com.scheduler.app.constants.USER_TYPE;
import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginServiceImpl implements LoginService{

    public LoginResponse inputCredentials(LoginRequest loginRequest) {

        loginRequest.getPassword();


        if(Strings.isNotEmpty(loginRequest.getUserID()) && Strings.isNotEmpty(loginRequest.getPassword())){

            // Code to send the incoming values to back-end


            //todo change the return type & value
            //Currently returning the input values


            LoginResponse loginResponse = new LoginResponse();

            //Flag for the User type
            Boolean typeFlag = true;

            // If the User data from the DB is for Admin
            if(typeFlag){

                loginResponse.setStatus(HttpStatus.ACCEPTED);
                loginResponse.setValid(true);
                loginResponse.setUserType(USER_TYPE.ADMIN);

            } else if (!typeFlag){

                //If the User Data from the DB is for Supervisor

                loginResponse.setStatus(HttpStatus.ACCEPTED);
                loginResponse.setValid(true);
                loginResponse.setUserType(USER_TYPE.SUPERVISOR);

                //todo Add conditional block for Staff type
            } else {

                loginResponse.setStatus(HttpStatus.BAD_REQUEST);
                loginResponse.setValid(false);
                loginResponse.setUserType(USER_TYPE.INVALID);
            }


            return loginResponse;

        } else {

            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Reason");
        }
    }
}
