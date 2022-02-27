package com.scheduler.app.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.constants.USER_TYPE;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class LoginResponse {

    @Getter
    @Setter
    private REQUEST_STATUS status;

    @Getter
    @Setter
    private boolean isValid;

    @Getter
    @Setter
    private USER_TYPE userType;
}
