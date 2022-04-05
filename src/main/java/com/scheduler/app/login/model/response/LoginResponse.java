package com.scheduler.app.login.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.constants.USER_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type - Response for the API for Login.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private REQUEST_STATUS status;
    private boolean isValid;
    private USER_TYPE userType;
    private Integer id;
    private String employeeNumber;
    private String departmentId;
}
