package com.scheduler.app.login.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The type - Request Object for Login Functionality
 */
@Getter
@Setter
public class LoginRequest {

    private String userID;
    private String password;

}
