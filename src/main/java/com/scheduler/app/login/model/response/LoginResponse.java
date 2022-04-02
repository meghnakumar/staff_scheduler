package com.scheduler.app.login.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.constants.USER_TYPE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private REQUEST_STATUS status;
    private boolean isValid;
    private USER_TYPE userType;
    private Integer id;
    private String employeeNumber;
    private String departmentId;
}
