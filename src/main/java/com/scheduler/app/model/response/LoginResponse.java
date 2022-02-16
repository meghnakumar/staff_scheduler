package com.scheduler.app.model.response;

import com.scheduler.app.constants.USER_TYPE;
import org.springframework.http.HttpStatus;

public class LoginResponse {

    private HttpStatus status;
    private boolean isValid;
    private USER_TYPE userType;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }
}
